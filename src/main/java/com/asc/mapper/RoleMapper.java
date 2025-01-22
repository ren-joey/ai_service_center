package com.asc.mapper;

import com.asc.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    void insert(Role role);

    void update(Role role);
}
