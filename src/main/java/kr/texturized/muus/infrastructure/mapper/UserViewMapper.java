package kr.texturized.muus.infrastructure.mapper;

import java.util.Optional;
import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.domain.entity.UserTypeEnum;
import org.apache.ibatis.annotations.Mapper;

/**
 * MyBatis read mapper for User.
 */
@Mapper
public interface UserViewMapper {

    boolean existsByAccountId(final String accountId);

    boolean existsByNickname(final String nickname);

    Optional<User> findByAccountId(final String accountId);

    Optional<User> findById(final Long userId);

    UserTypeEnum findUserTypeByAccountId(final String accountId);
}
