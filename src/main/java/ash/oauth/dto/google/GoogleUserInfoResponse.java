package ash.oauth.dto.google;

import ash.oauth.domain.SocialType;
import ash.oauth.dto.UserInfo;
import ash.oauth.dto.UserInfoResponse;

public record GoogleUserInfoResponse(
    String id,
    String name,
    String picture
) implements UserInfoResponse {

    @Override
    public UserInfo toUserInfo() {
        return new UserInfo(
            id,
            SocialType.GOOGLE,
            name,
            picture
        );
    }
}
