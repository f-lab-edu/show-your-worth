package kr.texturized.muus.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import kr.texturized.muus.infrastructure.repository.converter.type.UserTypeConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Entity for users.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"nickname"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String accountId;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String nickname;

    private String profileImagePath;

    /**
     * JPA enum Converting.
     * ref: <a href="https://studyandwrite.tistory.com/496">
     *          [Spring] JPA Enum Converter를 사용하여 Entity Mapping하기
     *          (Feat. Parameter value [~] did not match expected type Error)
     *           - 조성현 개발 컨테이너
     *     </a>
     */
    @Convert(converter = UserTypeConverter.class)
    @Column(nullable = false)
    private UserTypeEnum userType;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;

    @Builder
    public User(
        final String accountId,
        final String password,
        final String nickname,
        final String profileImagePath,
        final UserTypeEnum userType
    ) {
        this.accountId = accountId;
        this.password = password;
        this.nickname = nickname;
        this.profileImagePath = profileImagePath;
        this.userType = userType;
    }
}
