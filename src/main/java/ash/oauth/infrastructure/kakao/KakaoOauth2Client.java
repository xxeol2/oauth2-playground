package ash.oauth.infrastructure.kakao;

import ash.oauth.domain.Oauth2Client;
import ash.oauth.domain.SocialType;
import ash.oauth.dto.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class KakaoOauth2Client implements Oauth2Client {

    private final KakaoOauth2TokenClient tokenClient;
    private final KakaoOauth2UserInfoClient userInfoClient;

    public KakaoOauth2Client(KakaoOauth2TokenClient tokenClient,
                             KakaoOauth2UserInfoClient userInfoClient) {
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
        return SocialType.KAKAO;
    }
}
