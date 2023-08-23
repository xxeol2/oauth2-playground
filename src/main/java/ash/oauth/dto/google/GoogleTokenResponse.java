package ash.oauth.dto.google;

import ash.oauth.dto.TokenResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public record GoogleTokenResponse(
    @JsonProperty("token_type") String tokenType,
    @JsonProperty("access_token") String accessToken,
    @JsonProperty("expires_in") Integer expiresIn,
    @JsonProperty("refresh_token") String refreshToken,
    @JsonProperty("scope") String scope
) implements TokenResponse {

    @Override
    public String toAccessToken() {
        return accessToken;
    }
}
