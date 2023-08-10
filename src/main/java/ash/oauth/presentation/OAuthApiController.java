package ash.oauth.presentation;

import ash.oauth.application.OAuthService;
import ash.oauth.dto.LoginRequest;
import ash.oauth.dto.LoginResponse;
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

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        return oAuthService.login(request);
    }
}
