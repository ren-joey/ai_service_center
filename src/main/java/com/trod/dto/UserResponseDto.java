package com.trod.dto;

import com.trod.constant.RoleEnum;
import com.trod.entity.GameRole;
import com.trod.entity.User;

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
                user.getGameRole().getRole()
        );
    }
}
