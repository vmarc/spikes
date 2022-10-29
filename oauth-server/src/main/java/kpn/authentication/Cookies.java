package kpn.authentication;

import org.apache.tomcat.util.http.Rfc6265CookieProcessor;

import javax.servlet.http.Cookie;
import java.time.Duration;
import java.util.Optional;

import static java.util.Objects.isNull;

public class Cookies {

    public static Optional<String> get(final Cookie[] cookies, final String name) {
        if (isNull(cookies)) {
            return Optional.empty();
        }
        for (final Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase(name)) {
                return Optional.ofNullable(cookie.getValue());
            }
        }
        return Optional.empty();
    }

    public static String build(final String name, final String value, final Duration maxAge) {
        final Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge((int) maxAge.toSeconds());
        cookie.setPath("/");
        final Rfc6265CookieProcessor processor = new Rfc6265CookieProcessor();
        return processor.generateHeader(cookie);
    }

    public static String buildExpiredCookie(final String name) {
        return build(name, "-", Duration.ZERO);
    }

}
