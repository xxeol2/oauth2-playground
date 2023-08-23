package ash.oauth.config;

import ash.oauth.domain.Oauth2Property;
import ash.oauth.domain.Oauth2Property.EnvironmentProperties;
import ash.oauth.domain.Oauth2Property.ProviderProperties;
import ash.oauth.domain.SocialType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2")
public class Oauth2Properties {

    private final Map<SocialType, EnvironmentProperties> environment = new HashMap<>();
    private final Map<SocialType, ProviderProperties> provider = new HashMap<>();

    public Map<SocialType, Oauth2Property> getOauth2Properties() {
        return Arrays.stream(SocialType.values())
            .collect(Collectors.toMap(
                socialType -> socialType,
                socialType -> new Oauth2Property(socialType, environment.get(socialType), provider.get(socialType))
            ));
    }

    public Map<SocialType, EnvironmentProperties> getEnvironment() {
        return environment;
    }

    public Map<SocialType, ProviderProperties> getProvider() {
        return provider;
    }
}
