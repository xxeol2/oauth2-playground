package ash.oauth.dto.naver;

import ash.oauth.dto.TokenResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public record NaverTokenResponse(
    @JsonProperty("access_token") String accessToken,
    @JsonProperty("refresh_token") String refreshToken,
    @JsonProperty("token_type") String tokenType,
    @JsonProperty("expires_in") String expiresIn
) implements TokenResponse {

    @Override
    public String toAccessToken() {
        return accessToken;
    }
}
