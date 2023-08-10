package ash.oauth.infrastructure.kakao;

import ash.oauth.dto.kakao.KakaoTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class KakaoOauth2TokenClient {

    private static final String REQUEST_URL = "https://kauth.kakao.com/oauth/token";

    private final String grantType;
    private final String kakaoClientId;
    private final String kakaoRedirectUri;

    public KakaoOauth2TokenClient(
        @Value("${kakao.grant-type}") String grantType,
        @Value("${kakao.client-id}") String kakaoClientId,
        @Value("${kakao.redirect-uri}") String kakaoRedirectUri
    ) {
        this.grantType = grantType;
        this.kakaoClientId = kakaoClientId;
        this.kakaoRedirectUri = kakaoRedirectUri;
    }

    // https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#request-token
    public String request(String code) {
        HttpHeaders headers = getRequestHeader();
        MultiValueMap<String, String> body = getRequestBody(code);

        KakaoTokenResponse response = new RestTemplateBuilder()
            .build()
            .postForEntity(
                REQUEST_URL,
                new HttpEntity<>(body, headers),
                KakaoTokenResponse.class
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
        formData.add("grant_type", grantType);
        formData.add("client_id", kakaoClientId);
        formData.add("redirect_uri", kakaoRedirectUri);
        formData.add("code", code);
        return formData;
    }
}
