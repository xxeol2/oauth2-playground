package ash.oauth.presentation;

import ash.oauth.application.OauthService;
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
public class OauthApiController {

    private final OauthService oauthService;

    public OauthApiController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/redirect/{socialType}")
    public ResponseEntity<Void> redirectToAuthUri(@PathVariable SocialType socialType, HttpServletResponse response)
        throws IOException {
        String authUri = oauthService.redirect(socialType);
        response.sendRedirect(authUri);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return oauthService.login(request);
    }
}
