package ash.oauth.domain;

public record Oauth2Property(
    SocialType socialType,
    EnvironmentProperties environment,
    ProviderProperties provider
) {

    public record EnvironmentProperties(
        String clientId,
        String clientSecret,
        String redirectUri,
        String scope
    ) {

    }

    public record ProviderProperties(
        String authUri,
        String tokenUri,
        String userInfoUri
    ) {

    }
}
