package ash.oauth.dto;

import ash.oauth.domain.SocialType;

public record LoginRequest(
    SocialType socialType,
    String code
) {

}
