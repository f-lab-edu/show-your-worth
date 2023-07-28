package com.texturized.muus.domain.user.dao;

import com.texturized.muus.domain.user.domain.User;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserViewMapper {
    List<User> getList(Map<String, ?> params);

    Integer getCount(Map<String, ?> params);
}
