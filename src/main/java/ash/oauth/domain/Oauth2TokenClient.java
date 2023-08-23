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

    private final Oauth2Property oauth2Property;

    public Oauth2TokenClient(Oauth2Property oauth2Property) {
        this.oauth2Property = oauth2Property;
    }

    public String request(String code) {
        HttpHeaders headers = getRequestHeader();
        MultiValueMap<String, String> body = getRequestBody(code);

        TokenResponse response = new RestTemplateBuilder()
            .build()
            .postForEntity(
                oauth2Property.provider().tokenUri(),
                new HttpEntity<>(body, headers),
                oauth2Property.socialType().getTokenResponse()
            ).getBody();

        if (response == null) {
            throw new IllegalArgumentException("소셜의 Token 응답값이 잘못되었습니다.");
        }

        return response.toAccessToken();
    }

    private HttpHeaders getRequestHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    private MultiValueMap<String, String> getRequestBody(String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        EnvironmentProperties environment = oauth2Property.environment();
        formData.add("grant_type", GRANT_TYPE);
        formData.add("client_id", environment.clientId());
        formData.add("client_secret", environment.clientSecret());
        formData.add("redirect_uri", environment.redirectUri());
        formData.add("code", code);
        return formData;
    }
}
