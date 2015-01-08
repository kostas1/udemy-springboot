package springboot.data.entities;

import javax.persistence.*;

@Entity
@IdClass(UserConnectionId.class)
@Table(name = "userconnection", uniqueConstraints = @UniqueConstraint(columnNames = { "userid", "providerid", "rank" }))
public class UserConnection {

    @Id
    @Column(name = "userid", nullable = false, length = 255)
    private String userId;

    @Id
    @Column(name = "providerid", nullable = false, length = 255)
    private String providerId;

    @Column(name = "provideruserid", nullable = true, length = 255)
    private String providerUserId;

    @Id
    @Column(name = "rank", nullable = false)
    private int rank;

    @Column(name = "displayname", nullable = true, length = 255)
    private String displayName;

    @Column(name = "profileurl", nullable = true, length = 512)
    private String profileUrl;

    @Column(name = "imageurl", nullable = true, length = 512)
    private String imageUrl;

    @Column(name = "accesstoken", nullable = false, length = 255)
    private String accessToken;

    @Column(name = "secret", nullable = true, length = 255)
    private String secret;

    @Column(name = "refreshtoken", nullable = true, length = 255)
    private String refreshToken;

    @Column(name = "expiretime", nullable = true)
    private long expireTime;
}

