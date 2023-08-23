package ash.oauth.presentation;

import ash.oauth.application.OAuthService;
import ash.oauth.domain.SocialType;
import ash.oauth.dto.LoginRequest;
import ash.oauth.dto.LoginResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth/api")
public class OAuthApiController {

    private final OAuthService oAuthService;

    public OAuthApiController(OAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    @GetMapping("/redirect/{socialType}")
    public ResponseEntity<Void> oathRedirectUri(@PathVariable SocialType socialType, HttpServletResponse response)
        throws IOException {
        String kakaoAuthUri = oAuthService.redirect(socialType);
        response.sendRedirect(kakaoAuthUri);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return oAuthService.login(request);
    }
}
