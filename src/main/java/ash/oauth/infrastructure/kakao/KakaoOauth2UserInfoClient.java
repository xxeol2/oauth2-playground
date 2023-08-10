package ash.oauth.infrastructure.kakao;

import ash.oauth.dto.UserInfo;
import ash.oauth.dto.kakao.KakaoUserInfoResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class KakaoOauth2UserInfoClient {

    private static final String REQUEST_URL = "https://kapi.kakao.com/v2/user/me";

    // https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#req-user-info
    public UserInfo request(String accessToken) {
        HttpHeaders headers = getHttpHeaders(accessToken);

        KakaoUserInfoResponse response = new RestTemplateBuilder()
            .build()
            .postForEntity(
                REQUEST_URL,
                new HttpEntity<>(headers),
                KakaoUserInfoResponse.class
            )
            .getBody();

        return response.toUserInfo();
    }

    private HttpHeaders getHttpHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(accessToken);
        return headers;
    }
}
