package ash.oauth.application;

import ash.oauth.domain.Member;
import ash.oauth.domain.MemberRepository;
import ash.oauth.domain.Oauth2Client;
import ash.oauth.domain.SocialType;
import ash.oauth.dto.LoginRequest;
import ash.oauth.dto.LoginResponse;
import ash.oauth.dto.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class OauthService {

    private final Oauth2Client oauth2Client;
    private final MemberRepository memberRepository;

    public OauthService(Oauth2Client oauth2Client, MemberRepository memberRepository) {
        this.oauth2Client = oauth2Client;
        this.memberRepository = memberRepository;
    }

    public String redirect(SocialType socialType) {
        return oauth2Client.getAuthUri(socialType);
    }

    public LoginResponse login(LoginRequest request) {
        UserInfo userInfo = oauth2Client.requestUserInfo(request.socialType(), request.code());
        return memberRepository.findBySocialIdAndSocialType(userInfo.socialId(), userInfo.socialType())
            .map(LoginResponse::logIn)
            .orElseGet(() -> LoginResponse.signUp(signUp(userInfo)));
    }

    private Member signUp(UserInfo userInfo) {
        return memberRepository.save(userInfo.toMember());
    }
}
