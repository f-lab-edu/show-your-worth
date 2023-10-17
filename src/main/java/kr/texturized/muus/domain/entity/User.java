package kr.texturized.muus.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import kr.texturized.muus.infrastructure.repository.converter.type.UserTypeConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"nickname", "email"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank
    @Column(name = "account_id")
    private String accountId;

    @NotBlank
    @Column(name = "password")
    private String password;

    @NotBlank
    @Column(name = "nickname")
    private String nickname;

    @NotBlank
    @Column(name = "email_account", unique = true)
    private String email;

    @Column (name = "profile_image_path")
    private String profileImage;

    /**
     * JPA enum Converting.
     * ref: <a href="https://studyandwrite.tistory.com/496">
     *          [Spring] JPA Enum Converter를 사용하여 Entity Mapping하기
     *          (Feat. Parameter value [~] did not match expected type Error)
     *           - 조성현 개발 컨테이너
     *     </a>
     */
    @Convert(converter = UserTypeConverter.class)
    @Column(name = "category_id", nullable = false, updatable = true)
    private UserTypeEnum userType;

    @CreationTimestamp
    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;

    @Builder
    public User(
        final String accountId,
        final String password,
        final String nickname,
        final String email,
        final String profileImage,
        final UserTypeEnum userType
    ) {
        this.accountId = accountId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
        this.userType = userType;
    }
}
