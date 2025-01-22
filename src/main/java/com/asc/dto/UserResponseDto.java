package com.asc.dto;

import com.asc.constant.RoleEnum;
import com.asc.entity.User;

public record UserResponseDto (
        Long id,
        String username,
        String email,
        RoleEnum role
) {
    public static UserResponseDto convert(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().getRole()
        );
    }
}
