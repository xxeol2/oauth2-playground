package ash.oauth.dto;

import ash.oauth.domain.Member;

public record LoginResponse(
    Long memberId,
    Boolean isNew
) {

    public static LoginResponse logIn(Member member) {
        return new LoginResponse(
            member.getId(),
            false
        );
    }

    public static LoginResponse signUp(Member member) {
        return new LoginResponse(
            member.getId(),
            true
        );
    }
}
