package kpn.authentication;

import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Base64;
import java.util.Optional;

/*
    The AuthorizationRequestRepository is used to 'remember' the initial original authorization
    request for the duration of the entire authorization flow. The regular implemenation uses
    the http session to store this information. This implementation uses a cookie on the
    browser to keep the data during the entire flow (or for maximum 5 minutes), and also
    remembers the url to return to after the authorization flow.
 */
@Component
public class CustomAuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    private static final Duration OAUTH_COOKIE_EXPIRY = Duration.ofMinutes(5);
    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    private final Crypto crypto;

    public CustomAuthorizationRequestRepository(final Crypto crypto) {
        this.crypto = crypto;
    }

    @Override
    public void saveAuthorizationRequest(
            final OAuth2AuthorizationRequest authorizationRequest,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) {

        if (authorizationRequest == null) {
            removeCookie(response);
            return;
        }

        String successUrl = "";
        final String[] successUrlParameterValues = request.getParameterMap().get("successUrl");
        if (successUrlParameterValues != null && successUrlParameterValues.length == 1) {
            successUrl = successUrlParameterValues[0];
        }

        String failureUrl = "";
        final String[] failureUrlParameterValues = request.getParameterMap().get("failureUrl");
        if (failureUrlParameterValues != null && failureUrlParameterValues.length == 1) {
            failureUrl = failureUrlParameterValues[0];
        }

        final OAuthCookieData cookieData = new OAuthCookieData(successUrl, failureUrl, authorizationRequest);

        final byte[] bytes = SerializationUtils.serialize(cookieData);
        final byte[] encryptedBytes = crypto.encrypt(bytes);
        final String encodedCookieData = encoder.encodeToString(encryptedBytes);
        final String cookie = Cookies.build(OAuthCookieData.COOKIE_NAME, encodedCookieData, OAUTH_COOKIE_EXPIRY);
        response.setHeader(HttpHeaders.SET_COOKIE, cookie);
    }

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(final HttpServletRequest request) {
        return retrieveCookie(request).map(OAuthCookieData::getAuthorizationRequest).orElse(null);
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(final HttpServletRequest request) {
        // we retrieve the authorizationRequest, but do not delete the cookie yet, we still need the return url
        return retrieveCookie(request).map(OAuthCookieData::getAuthorizationRequest).orElse(null);
    }

    public Optional<OAuthCookieData> retrieveCookie(final HttpServletRequest request) {
        return Cookies.get(request.getCookies(), OAuthCookieData.COOKIE_NAME)
                .map(this::decrypt);
    }

    public void removeCookie(final HttpServletResponse response) {
        final String expiredCookie = Cookies.buildExpiredCookie(OAuthCookieData.COOKIE_NAME);
        response.setHeader(HttpHeaders.SET_COOKIE, expiredCookie);
    }

    private OAuthCookieData decrypt(final String encrypted) {
        final byte[] encryptedBytes = decoder.decode(encrypted);
        final byte[] bytes = crypto.decrypt(encryptedBytes);
        return  (OAuthCookieData) SerializationUtils.deserialize(bytes);
    }
}
