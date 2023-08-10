package ash.oauth.infrastructure.google;

import ash.oauth.dto.google.GoogleTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class GoogleOauth2TokenClient {

    private static final String REQUEST_URL = "https://oauth2.googleapis.com/token";

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String grantType;

    public GoogleOauth2TokenClient(
        @Value("${google.client-id}") String clientId,
        @Value("${google.client-secret}") String clientSecret,
        @Value("${google.redirect-uri}") String redirectUri,
        @Value("${google.grant-type}") String grantType
    ) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.grantType = grantType;
    }

    // https://developers.google.com/identity/protocols/oauth2/web-server?hl=ko#obtainingaccesstokens
    public String request(String code) {
        HttpHeaders headers = getRequestHeader();
        MultiValueMap<String, String> body = getRequestBody(code);

        GoogleTokenResponse response = new RestTemplateBuilder()
            .build()
            .postForEntity(
                REQUEST_URL,
                new HttpEntity<>(body, headers),
                GoogleTokenResponse.class
            )
            .getBody();
        return response.accessToken();
    }

    private HttpHeaders getRequestHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    private MultiValueMap<String, String> getRequestBody(String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("code", code);
        formData.add("redirect_uri", redirectUri);
        formData.add("grant_type", grantType);
        return formData;
    }
}
