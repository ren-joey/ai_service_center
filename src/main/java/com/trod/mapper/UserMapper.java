package com.trod.mapper;

import com.trod.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> findById(@Param("id") Long id);

    Optional<User> findByEmail(@Param("email") String email);

    Optional<User> findByUsername(@Param("username") String username);

    List<User> findAll();

    void insert(User user);

    void update(User user);

    void deleteById(@Param("id") Long id);
}
