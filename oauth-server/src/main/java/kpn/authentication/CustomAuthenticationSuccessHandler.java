package kpn.authentication;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final CustomAuthorizationRequestRepository authorizationRequestRepository;

    public CustomAuthenticationSuccessHandler(final CustomAuthorizationRequestRepository authorizationRequestRepository) {
        this.authorizationRequestRepository = authorizationRequestRepository;
    }

    @Override
    public void onAuthenticationSuccess(
            final HttpServletRequest authenticationRequest,
            final HttpServletResponse response,
            final Authentication authentication
    ) {
        authorizationRequestRepository.retrieveCookie(authenticationRequest).ifPresent(oAuthCookieData -> {
            final DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
            final String name = user.getName();
            final String cookie = Cookies.build("kpn-user", name, Duration.of(365, ChronoUnit.DAYS));
            authorizationRequestRepository.removeCookie(response);
            response.addHeader(HttpHeaders.SET_COOKIE, cookie);
            final String successUrl = oAuthCookieData.getSuccessUrl();
            try {
                response.sendRedirect(successUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
