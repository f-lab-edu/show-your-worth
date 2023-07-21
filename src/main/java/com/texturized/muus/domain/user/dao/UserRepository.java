package com.texturized.muus.domain.user.dao;

import com.texturized.muus.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
