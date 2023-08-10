package ash.oauth.infrastructure.google;

import ash.oauth.dto.UserInfo;
import ash.oauth.dto.google.GoogleUserInfoResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class GoogleOauth2UserInfoClient {

    private static final String REQUEST_URL = "https://www.googleapis.com/oauth2/v2/userinfo";

    public UserInfo request(String accessToken) {
        HttpHeaders headers = getHttpHeaders(accessToken);
        GoogleUserInfoResponse response = new RestTemplateBuilder()
            .build()
            .exchange(
                REQUEST_URL,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                GoogleUserInfoResponse.class
            )
            .getBody();
        return response.toUserInfo();
    }

    private HttpHeaders getHttpHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        return headers;
    }
}
