package kr.texturized.muus.infrastructure.mapper;

import java.util.Optional;
import kr.texturized.muus.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * MyBatis read mapper for User.
 */
@Mapper
public interface UserViewMapper {

    boolean existsByAccountId(final String accountId);

    boolean existsByNickname(final String nickname);

    boolean existsByEmail(final String email);

    Optional<User> findByAccountId(final String accountId);

    Optional<User> findById(final Long userId);
}
