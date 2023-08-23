package ash.oauth.dto;

import ash.oauth.domain.SocialType;

public record RedirectUriRequest(
    SocialType socialType,
    String redirectUri
) {

}
