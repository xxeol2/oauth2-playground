package ash.oauth.infrastructure.google;

import ash.oauth.domain.Oauth2Client;
import ash.oauth.domain.SocialType;
import ash.oauth.dto.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class GoogleOauth2Client implements Oauth2Client {

    private final GoogleOauth2TokenClient tokenClient;
    private final GoogleOauth2UserInfoClient userInfoClient;

    public GoogleOauth2Client(GoogleOauth2TokenClient tokenClient,
                              GoogleOauth2UserInfoClient userInfoClient) {
        this.tokenClient = tokenClient;
        this.userInfoClient = userInfoClient;
    }

    @Override
    public String requestToken(String code) {
        return tokenClient.request(code);
    }

    @Override
    public UserInfo requestUserInfo(String accessToken) {
        return userInfoClient.request(accessToken);
    }

    @Override
    public SocialType getSocialType() {
        return SocialType.GOOGLE;
    }
}
