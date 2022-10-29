package kpn.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomAuthorizationRequestRepository authorizationRequestRepository;
    private final CustomAuthenticationSuccessHandler successHandler;
    private final CustomAuthenticationFailureHandler failureHandler;
    private final CustomUserService userService;

    public SecurityConfig(
            final CustomAuthorizationRequestRepository authorizationRequestRepository,
            final CustomAuthenticationSuccessHandler successHandler,
            final CustomAuthenticationFailureHandler failureHandler,
            final CustomUserService userService
    ) {
        this.authorizationRequestRepository = authorizationRequestRepository;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(config -> config.anyRequest().permitAll())
                .oauth2Login(config -> {
                    config.userInfoEndpoint().userService(userService);
                    config.successHandler(successHandler);
                    config.failureHandler(failureHandler);
                    config.authorizationEndpoint(subconfig -> {
                        subconfig.authorizationRequestRepository(authorizationRequestRepository);
                    });
                });
        return http.build();
    }
}
