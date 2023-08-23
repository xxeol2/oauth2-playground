package ash.oauth.dto.naver;

import ash.oauth.domain.SocialType;
import ash.oauth.dto.UserInfo;
import ash.oauth.dto.UserInfoResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public record NaverUserInfoResponse(
    String resultcode,
    String message,
    Response response
) implements UserInfoResponse {

    @Override
    public UserInfo toUserInfo() {
        return new UserInfo(
            response.id,
            SocialType.NAVER,
            response.name,
            response.profileImage
        );
    }

    private record Response(
        String id,
        String name,
        @JsonProperty("profile_image") String profileImage
    ) {

    }
}
