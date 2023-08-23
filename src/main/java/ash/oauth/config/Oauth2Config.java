package ash.oauth.config;

import ash.oauth.domain.Oauth2Client;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Oauth2Properties.class)
public class Oauth2Config {

    private final Oauth2Properties oauth2Properties;

    public Oauth2Config(Oauth2Properties oauth2Properties) {
        this.oauth2Properties = oauth2Properties;
    }

    @Bean
    public Oauth2Client oauth2Client() {
        return new Oauth2Client(oauth2Properties.getOauth2Properties());
    }
}
