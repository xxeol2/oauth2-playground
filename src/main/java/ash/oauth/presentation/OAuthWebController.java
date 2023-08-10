package ash.oauth.presentation;

import ash.oauth.domain.SocialType;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/oauth")
public class OAuthWebController {

    @Value("${google.client-id}")
    private String googleClientId;
    @Value("${google.redirect-uri}")
    private String googleRedirectUri;
    @Value("${kakao.client-id}")
    private String kakaoClientId;
    @Value("${kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @GetMapping
    public String authorize(Model model) {
        Oauth2Provider kakaoProvider = new Oauth2Provider(
            SocialType.KAKAO,
            "https://kauth.kakao.com/oauth/authorize",
            "code",
            kakaoClientId,
            kakaoRedirectUri,
            null
        );

        Oauth2Provider googleProvider = new Oauth2Provider(
            SocialType.GOOGLE,
            "https://accounts.google.com/o/oauth2/v2/auth",
            "code",
            googleClientId,
            googleRedirectUri,
            "https://www.googleapis.com/auth/userinfo.profile"
        );

        model.addAttribute("providers", List.of(kakaoProvider, googleProvider));

        return "authorize";
    }

    @GetMapping("/{socialType}/redirect")
    public String handleKakaoCallback(@PathVariable String socialType,
                                      @RequestParam("code") String code,
                                      Model model) {
        String targetUri = "http://localhost:8080/oauth/api";

        model.addAttribute("socialType", SocialType.valueOf(socialType.toUpperCase()));
        model.addAttribute("targetUri", targetUri);
        model.addAttribute("code", code);
        return "handler";
    }

    public record Oauth2Provider(
        SocialType socialType,
        String authUrl,
        String responseType,
        String clientId,
        String redirectUri,
        String scope
    ) {

    }
}
