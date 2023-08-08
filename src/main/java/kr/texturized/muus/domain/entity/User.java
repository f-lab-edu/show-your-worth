package kr.texturized.muus.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"email", "nickname"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "account_id", nullable = false, unique = true, updatable = false, length = 15))
    private AccountID accountID;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password", nullable = false, updatable = true, length = 20))
    private Password password;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "nickname", nullable = false, unique = true, updatable = true, length = 15))
    private Nickname nickname;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email_account", nullable = false, unique = true, updatable = false, length = 45))
    private Email email;

    @Embedded
    @AttributeOverride(name = "value", column = @Column (name = "profile_image_path", nullable = true, updatable = true, length = 250))
    private ProfileImage profileImage;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category_id", nullable = false, updatable = true)
    private UserType userType;

    @CreationTimestamp
    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;

    @Builder
    public User(
        AccountID accountID,
        Password password,
        Nickname nickname,
        Email email,
        ProfileImage profileImage,
        UserType userType) {
        this.accountID = accountID;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
        this.userType = userType;
    }
}
