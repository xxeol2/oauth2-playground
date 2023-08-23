package ash.oauth.application;

import ash.oauth.domain.Member;
import ash.oauth.domain.MemberRepository;
import ash.oauth.domain.Oauth2Clients;
import ash.oauth.domain.SocialType;
import ash.oauth.dto.LoginRequest;
import ash.oauth.dto.LoginResponse;
import ash.oauth.dto.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    private final Oauth2Clients oauth2Clients;
    private final MemberRepository memberRepository;

    public OAuthService(Oauth2Clients oauth2Clients, MemberRepository memberRepository) {
        this.oauth2Clients = oauth2Clients;
        this.memberRepository = memberRepository;
    }

    public String redirect(SocialType socialType) {
        return oauth2Clients.getRedirectUri(socialType);
    }

    public LoginResponse login(LoginRequest request) {
        UserInfo userInfo = oauth2Clients.requestUserInfo(request.socialType(), request.code());
        return memberRepository.findBySocialIdAndSocialType(userInfo.socialId(), userInfo.socialType())
            .map(LoginResponse::logIn)
            .orElseGet(() -> LoginResponse.signUp(signUp(userInfo)));
    }

    private Member signUp(UserInfo userInfo) {
        return memberRepository.save(userInfo.toMember());
    }
}
