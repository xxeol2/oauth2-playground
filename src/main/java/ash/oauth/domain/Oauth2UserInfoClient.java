package ash.oauth.domain;

import ash.oauth.dto.UserInfo;
import ash.oauth.dto.UserInfoResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public class Oauth2UserInfoClient {

    public static UserInfo request(Oauth2Property property, String accessToken) {
        HttpHeaders headers = getHttpHeaders(accessToken);

        UserInfoResponse response = new RestTemplateBuilder()
            .build()
            .exchange(
                property.provider().userInfoUri(),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                property.socialType().getUserInfoResponse()
            )
            .getBody();

        if (response == null) {
            throw new IllegalArgumentException("소셜의 UserInfo 응답값이 잘못되었습니다.");
        }

        return response.toUserInfo();
    }

    private static HttpHeaders getHttpHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        return headers;
    }
}
