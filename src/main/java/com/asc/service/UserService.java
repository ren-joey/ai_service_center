package com.asc.service;

import com.asc.dto.RegisterRequestDto;
import com.asc.mapper.RoleMapper;
import com.asc.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleMapper roleMapper;
    private final AuthService authService;

    public void addUser(RegisterRequestDto registerRequestDto) {
        authService.register(registerRequestDto);
    }


}
