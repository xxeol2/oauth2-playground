package ash.oauth.domain;

import ash.oauth.dto.UserInfo;

public interface Oauth2Client {

    String requestToken(String code);

    UserInfo requestUserInfo(String accessToken);

    SocialType getSocialType();
}
