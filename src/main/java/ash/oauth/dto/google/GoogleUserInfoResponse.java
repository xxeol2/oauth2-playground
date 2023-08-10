package ash.oauth.dto.google;

import ash.oauth.domain.SocialType;
import ash.oauth.dto.UserInfo;

public record GoogleUserInfoResponse(
    String id,
    String name,
    String picture
) {

    public UserInfo toUserInfo() {
        return new UserInfo(
            id,
            SocialType.GOOGLE,
            name,
            picture
        );
    }
}
