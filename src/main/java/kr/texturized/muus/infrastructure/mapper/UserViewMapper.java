package kr.texturized.muus.infrastructure.mapper;

import kr.texturized.muus.domain.entity.User;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserViewMapper {

    boolean existsByAccountId(final String accountId);

    boolean existsByNickname(final String nickname);

    boolean existsByEmail(final String email);

    List<User> getList(Map<String, ?> params);

    Integer getCount(Map<String, ?> params);
}
