package com.trod.service;

import com.trod.dto.RegisterRequestDto;
import com.trod.entity.GameRole;
import com.trod.mapper.GameRoleMapper;
import com.trod.mapper.UserMapper;
import com.trod.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final GameRoleMapper gameRoleMapper;
    private final AuthService authService;

    public void addUser(RegisterRequestDto registerRequestDto) {
        authService.register(registerRequestDto);
    }


}
