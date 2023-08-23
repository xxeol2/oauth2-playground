package ash.oauth.domain;

import ash.oauth.dto.UserInfo;
import org.springframework.web.util.UriComponentsBuilder;

public class Oauth2Client {

    private final Oauth2Property property;
    private final Oauth2TokenClient tokenClient;
    private final Oauth2UserInfoClient userInfoClient;

    public Oauth2Client(Oauth2Property property) {
        this.property = property;
        this.tokenClient = new Oauth2TokenClient(property);
        this.userInfoClient = new Oauth2UserInfoClient(property);
    }

    public String getRedirectUri() {
        return UriComponentsBuilder.fromHttpUrl(property.provider().authUri())
            .queryParam("client_id", property.environment().clientId())
            .queryParam("redirect_uri", property.environment().redirectUri())
            .queryParam("response_type", "code")
            .queryParam("scope", property.environment().scope())
            .build()
            .toUriString();
    }

    public String requestToken(String code) {
        return tokenClient.request(code);
    }

    public UserInfo requestUserInfo(String accessToken) {
        return userInfoClient.request(accessToken);
    }
}
