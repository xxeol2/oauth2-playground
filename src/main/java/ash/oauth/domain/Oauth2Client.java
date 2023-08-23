package ash.oauth.domain;

import ash.oauth.dto.UserInfo;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.springframework.web.util.UriComponentsBuilder;

public class Oauth2Client {

    private final Map<SocialType, Oauth2Property> properties;

    public Oauth2Client(Map<SocialType, Oauth2Property> properties) {
        this.properties = properties;
    }

    public String getAuthUri(SocialType socialType) {
        Oauth2Property property = getProperty(socialType);
        String redirectUri = property.environment().redirectUri();
        return UriComponentsBuilder.fromHttpUrl(property.provider().authUri())
            .queryParam("client_id", property.environment().clientId())
            .queryParam("redirect_uri", redirectUri)
            .queryParam("response_type", "code")
            .queryParam("scope", property.environment().scope())
            .queryParam("state", URLEncoder.encode(redirectUri, StandardCharsets.UTF_8))
            .build()
            .toUriString();
    }

    public UserInfo requestUserInfo(SocialType socialType, String code) {
        Oauth2Property property = getProperty(socialType);
        String accessToken = Oauth2TokenClient.request(property, code);
        return Oauth2UserInfoClient.request(property, accessToken);
    }

    private Oauth2Property getProperty(SocialType socialType) {
        return properties.get(socialType);
    }
}
