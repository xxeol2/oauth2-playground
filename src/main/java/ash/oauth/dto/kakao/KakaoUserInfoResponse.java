package ash.oauth.dto.kakao;

import ash.oauth.domain.SocialType;
import ash.oauth.dto.UserInfo;
import ash.oauth.dto.kakao.KakaoUserInfoResponse.KakaoAccount.Profile;
import com.fasterxml.jackson.annotation.JsonProperty;

// https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#req-user-info-response-body
public record KakaoUserInfoResponse(
    String id,
    @JsonProperty("kakao_account") KakaoAccount kakaoAccount
) {

    public UserInfo toUserInfo() {
        Profile profile = kakaoAccount.profile;
        return new UserInfo(
            id,
            SocialType.KAKAO,
            profile.nickname,
            profile.profileImageUrl
        );
    }

    public record KakaoAccount(
        Profile profile
    ) {

        public record Profile(
            String nickname,
            @JsonProperty("profile_image_url") String profileImageUrl
        ) {

        }
    }
}
