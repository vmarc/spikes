package kpn.authentication;

import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import java.io.Serializable;

/*
    The data that is kept in a cookie on the browser during the
    authentication flow.
 */
public class OAuthCookieData implements Serializable {

    public static final String COOKIE_NAME = "kpn-oauth";

    /*
        The url in the Angular application to return to after login.
     */
    private final String successUrl;
    private final String failureUrl;

    private final OAuth2AuthorizationRequest authorizationRequest;

    public OAuthCookieData(final String successUrl, final String failureUrl, final OAuth2AuthorizationRequest authorizationRequest) {
        this.successUrl = successUrl;
        this.failureUrl = failureUrl;
        this.authorizationRequest = authorizationRequest;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public OAuth2AuthorizationRequest getAuthorizationRequest() {
        return authorizationRequest;
    }
}
