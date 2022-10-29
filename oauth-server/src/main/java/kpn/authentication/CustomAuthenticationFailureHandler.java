package kpn.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final CustomAuthorizationRequestRepository authorizationRequestRepository;

    public CustomAuthenticationFailureHandler(final CustomAuthorizationRequestRepository authorizationRequestRepository) {
        this.authorizationRequestRepository = authorizationRequestRepository;
    }

    @Override
    public void onAuthenticationFailure(
            final HttpServletRequest authenticationRequest,
            final HttpServletResponse response,
            final AuthenticationException exception
    ) {
        authorizationRequestRepository.retrieveCookie(authenticationRequest).ifPresent(authCookieData -> {
            final String failureUrl = authCookieData.getFailureUrl();
            authorizationRequestRepository.removeCookie(response);
            try {
                response.sendRedirect(failureUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
