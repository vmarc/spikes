package kpn.authentication;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
    Picks up the username from OpenStreetMap using the access token that
    was previously obtained. We need this custom implementation to adapt
    the user information returned by OpenStreetMap to what normally would
    be expected by an authorization server.
 */
@Component
public class CustomUserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final OAuth2User user = super.loadUser(userRequest);
        final DefaultOAuth2User u = (DefaultOAuth2User) user;
        final Map<String, String> map = (Map<String, String>) u.getAttributes().get("user");
        final String name = map.get("display_name");
        final Map<String, Object> userAttributes = new HashMap<>();
        userAttributes.put("name", name);
        return new DefaultOAuth2User(new ArrayList<>(), userAttributes, "name");
    }
}
