package ash.oauth.domain;

import ash.oauth.dto.UserInfo;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Oauth2Clients {

    private final Map<SocialType, Oauth2Client> clients;

    public Oauth2Clients(Set<Oauth2Client> clients) {
        EnumMap<SocialType, Oauth2Client> mapping = new EnumMap<>(SocialType.class);
        clients.forEach(client -> mapping.put(client.getSocialType(), client));
        this.clients = mapping;
    }

    public UserInfo requestUserInfo(SocialType socialType, String code) {
        Oauth2Client client = getClient(socialType);
        String accessToken = client.requestToken(code);
        return client.requestUserInfo(accessToken);
    }

    private Oauth2Client getClient(SocialType socialType) {
        return Optional.ofNullable(clients.get(socialType))
            .orElseThrow(() -> new IllegalArgumentException("해당 OAuth2 제공자는 지원되지 않습니다."));
    }
}
