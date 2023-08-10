package ash.oauth.dto;

import ash.oauth.domain.Member;
import ash.oauth.domain.SocialType;

public record UserInfo(
    String socialId,
    SocialType socialType,
    String nickname,
    String profileImage
) {

    public Member toMember() {
        return new Member(
            socialId,
            socialType,
            nickname,
            profileImage
        );
    }
}
