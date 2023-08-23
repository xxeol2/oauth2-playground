package ash.oauth.presentation;

import ash.oauth.domain.SocialType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/oauth")
public class OauthWebController {

    @GetMapping("/login")
    public String login(Model model) {
        String targetUri = "/oauth/api/redirect";

        model.addAttribute("targetUri", targetUri);
        model.addAttribute("socialTypes", SocialType.values());
        return "login";
    }

    @GetMapping("/{socialType}/redirect")
    public String handleCallback(@PathVariable String socialType,
                                 @RequestParam("code") String code,
                                 Model model) {
        String targetUri = "/oauth/api/login";

        model.addAttribute("socialType", SocialType.valueOf(socialType.toUpperCase()));
        model.addAttribute("targetUri", targetUri);
        model.addAttribute("code", code);
        return "handler";
    }
}
