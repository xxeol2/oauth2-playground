package ash.oauth.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String socialId;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String nickname;

    private String profileImage;

    protected Member() {
    }

    public Member(String socialId, SocialType socialType, String nickname, String profileImage) {
        this(null, socialId, socialType, nickname, profileImage);
    }

    public Member(Long id, String socialId, SocialType socialType, String nickname, String profileImage) {
        this.id = id;
        this.socialId = socialId;
        this.socialType = socialType;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
    }

    public String getSocialId() {
        return socialId;
    }

    public SocialType getSocialType() {
        return socialType;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
