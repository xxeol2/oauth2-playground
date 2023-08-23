package ash.oauth.domain;

import ash.oauth.dto.TokenResponse;
import ash.oauth.dto.UserInfoResponse;
import ash.oauth.dto.google.GoogleTokenResponse;
import ash.oauth.dto.google.GoogleUserInfoResponse;
import ash.oauth.dto.kakao.KakaoTokenResponse;
import ash.oauth.dto.kakao.KakaoUserInfoResponse;

public enum SocialType {
    KAKAO(
        KakaoTokenResponse.class,
        KakaoUserInfoResponse.class
    ),
    GOOGLE(
        GoogleTokenResponse.class,
        GoogleUserInfoResponse.class),
    ;

    private final Class<? extends TokenResponse> tokenResponse;
    private final Class<? extends UserInfoResponse> userInfoResponse;

    SocialType(Class<? extends TokenResponse> tokenResponse,
               Class<? extends UserInfoResponse> userInfoResponse) {
        this.tokenResponse = tokenResponse;
        this.userInfoResponse = userInfoResponse;
    }

    public Class<? extends TokenResponse> getTokenResponse() {
        return tokenResponse;
    }

    public Class<? extends UserInfoResponse> getUserInfoResponse() {
        return userInfoResponse;
    }
}
