package com.trod.mapper;

import com.trod.entity.GameRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameRoleMapper {
    void insert(GameRole gameRole);

    void update(GameRole gameRole);
}
