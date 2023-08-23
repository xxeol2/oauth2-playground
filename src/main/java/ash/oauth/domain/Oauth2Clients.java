package ash.oauth.domain;

import ash.oauth.dto.UserInfo;
import java.util.Map;

public class Oauth2Clients {

    private final Map<SocialType, Oauth2Property> properties;

    public Oauth2Clients(Map<SocialType, Oauth2Property> properties) {
        this.properties = properties;
    }

    public String getRedirectUri(SocialType socialType) {
        Oauth2Client client = getClient(socialType);
        return client.getRedirectUri();
    }

    public UserInfo requestUserInfo(SocialType socialType, String code) {
        Oauth2Client client = getClient(socialType);
        String accessToken = client.requestToken(code);
        return client.requestUserInfo(accessToken);
    }

    private Oauth2Client getClient(SocialType socialType) {
        return new Oauth2Client(properties.get(socialType));
    }
}
