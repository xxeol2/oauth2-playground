package ash.oauth.presentation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/oauth")
public class OAuthWebController {

    @Value("${kakao.client-id}")
    private String kakaoClientId;

    @Value("${kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @GetMapping("/kakao")
    public String authorizeKakao(Model model) {
        String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize";
        String responseType = "code";

        model.addAttribute("kakaoAuthUrl", kakaoAuthUrl);
        model.addAttribute("responseType", responseType);
        model.addAttribute("clientId", kakaoClientId);
        model.addAttribute("redirectUri", kakaoRedirectUri);

        return "authorize_kakao";
    }

    @GetMapping("/kakao/redirect")
    public String handleKakaoCallback(@RequestParam("code") String code, Model model) {
        String targetUri = "http://localhost:8080/oauth/api";
        model.addAttribute("code", code);
        model.addAttribute("targetUri", targetUri);
        return "handle_kakao";
    }
}
