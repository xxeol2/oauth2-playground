package ash.oauth.config;

import ash.oauth.domain.Oauth2Client;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Oauth2ConfigProperties.class)
public class Oauth2Config {

    private final Oauth2ConfigProperties oauth2ConfigProperties;

    public Oauth2Config(Oauth2ConfigProperties oauth2ConfigProperties) {
        this.oauth2ConfigProperties = oauth2ConfigProperties;
    }

    @Bean
    public Oauth2Client oauth2Client() {
        return new Oauth2Client(oauth2ConfigProperties.getOauth2Properties());
    }
}
