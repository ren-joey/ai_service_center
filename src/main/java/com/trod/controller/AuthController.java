package com.trod.controller;

import com.trod.constant.RoleEnum;
import com.trod.dto.LoginRequestDto;
import com.trod.dto.RegisterRequestDto;
import com.trod.dto.UserResponseDto;
import com.trod.entity.User;
import com.trod.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final HttpServletResponse response;
    private final AuthService authService;

    @GetMapping("/users/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        authService.checkPermission(RoleEnum.GAME_MASTER);
        return UserResponseDto.convert(
                authService.getUserById(id)
        );
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAllUsers() {
        authService.checkPermission(RoleEnum.GAME_MASTER);
        return authService.getAllUsers()
                .stream()
                .map(UserResponseDto::convert)
                .collect(Collectors.toList());
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody RegisterRequestDto registerRequestDto) {
        authService.updateUser(id, registerRequestDto);
        return "User updated successfully!";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        authService.deleteUser(id);
        return "User deleted successfully!";
    }

    @PostMapping("/register")
    public UserResponseDto register(@Valid @RequestBody RegisterRequestDto registerRequest) {
        return UserResponseDto.convert(
                authService.register(registerRequest)
        );
    }

    @PostMapping("/login")
    public UserResponseDto login(@Valid @RequestBody LoginRequestDto loginRequest) {
        return UserResponseDto.convert(
                authService.login(loginRequest, response)
        );
    }

    @GetMapping("/logout")
    public void logout() {
        authService.logout(response);
    }

    @Profile("dev")
    @GetMapping("/checkJwtPayload")
    public Map<String, String> checkLogin () {
        return authService.checkJwtPayloadFromCookie();
    }

    @Profile("dev")
    @GetMapping("/checkLoggedInUser")
    public User checkLoggedInUser() {
        return authService.getLoggedInUserThrowable();
    }
}
