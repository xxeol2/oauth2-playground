package ash.oauth.config;

import ash.oauth.domain.Oauth2Client;
import ash.oauth.domain.Oauth2Clients;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Bean
    public Oauth2Clients oAuth2Clients(Set<Oauth2Client> clients) {
        return new Oauth2Clients(clients);
    }
}
