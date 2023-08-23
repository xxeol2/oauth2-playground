package ash.oauth.domain;

import ash.oauth.domain.Oauth2Property.EnvironmentProperties;
import ash.oauth.dto.TokenResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class Oauth2TokenClient {

    private static final String GRANT_TYPE = "authorization_code";

    public static String request(Oauth2Property property, String code) {
        HttpHeaders headers = getRequestHeader();
        MultiValueMap<String, String> body = getRequestBody(property, code);

        TokenResponse response = new RestTemplateBuilder()
            .build()
            .postForEntity(
                property.provider().tokenUri(),
                new HttpEntity<>(body, headers),
                property.socialType().getTokenResponse()
            ).getBody();

        if (response == null) {
            throw new IllegalArgumentException("소셜의 Token 응답값이 잘못되었습니다.");
        }

        return response.toAccessToken();
    }

    private static HttpHeaders getRequestHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    private static MultiValueMap<String, String> getRequestBody(Oauth2Property property, String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        EnvironmentProperties environment = property.environment();
        formData.add("grant_type", GRANT_TYPE);
        formData.add("client_id", environment.clientId());
        formData.add("client_secret", environment.clientSecret());
        formData.add("redirect_uri", environment.redirectUri());
        formData.add("code", code);
        return formData;
    }
}
