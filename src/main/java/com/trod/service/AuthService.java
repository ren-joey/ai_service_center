package com.trod.service;

import com.trod.constant.RoleEnum;
import com.trod.dto.LoginRequestDto;
import com.trod.dto.RegisterRequestDto;
import com.trod.dto.UserResponseDto;
import com.trod.entity.GameRole;
import com.trod.entity.User;
import com.trod.mapper.GameRoleMapper;
import com.trod.mapper.UserMapper;
import com.trod.security.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final GameRoleMapper gameRoleMapper;

    public User getUserById(Long id) {
        return userMapper.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    public void updateUser(Long id, RegisterRequestDto registerRequestDto) {
        User loggedUser = getLoggedInUser()
                .orElseThrow(() -> new InsufficientAuthenticationException("You are not logged in"));

        User user = getUserById(id);
        if (loggedUser.getGameRole().getRole() != RoleEnum.ADMIN
                || !loggedUser.getUuid().equals(user.getUuid())) {
            throw new InsufficientAuthenticationException("You are not authorized to update this user");
        }
        if (registerRequestDto.username() != null)
            user.setUsername(registerRequestDto.username());
        if (registerRequestDto.password() != null)
            user.setPassword(passwordEncoder.encode(registerRequestDto.password()));
        if (registerRequestDto.email() != null)
            user.setEmail(registerRequestDto.email());
        userMapper.update(user);

        if (registerRequestDto.role() != null) {
            GameRole gameRole = user.getGameRole();
            gameRole.setRole(registerRequestDto.role());
            gameRoleMapper.update(gameRole);
        }
    }

    public void deleteUser(Long id) {
        checkPermission(RoleEnum.ADMIN);
        userMapper.deleteById(id);
    }

    @Transactional
    public User register(RegisterRequestDto registerRequest) {
        if (userMapper.findByEmail(registerRequest.email()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        if (registerRequest.role().getIndex() > 0) {
            checkPermission(RoleEnum.fromIndex(registerRequest.role().getIndex() + 1));
        }

        User user = new User();
        user.setUsername(registerRequest.username());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setEmail(registerRequest.email());
        userMapper.insert(user);

        GameRole gameRole = new GameRole();
        gameRole.setRole(registerRequest.role());
        gameRole.setUser(user);
        user.setGameRole(gameRole);
        gameRoleMapper.insert(gameRole);

        return user;
    }

    public User login(LoginRequestDto loginRequest, HttpServletResponse response) {
        User user = userMapper.findByUsername(loginRequest.username())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            addJwtToCookie(response, token);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return user;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public void logout(HttpServletResponse response) {
        Optional<User> user = getLoggedInUser();
        if (user.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("You are not logged in");
        }
        Cookie cookie = new Cookie(System.getProperty("JWT_KEY_NAME"), "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public void addJwtToCookie(HttpServletResponse response, String jwt) {
        Cookie cookie = new Cookie(System.getProperty("JWT_KEY_NAME"), jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    public Optional<User> getLoggedInUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return Optional.empty();
        }
        return userMapper.findByUsername(authentication.getPrincipal().toString());
    }

    public User getLoggedInUserThrowable() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new InsufficientAuthenticationException("You are not logged in");
        }
        return userMapper.findByUsername(authentication.getPrincipal().toString())
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not found"));
    }

    public User checkPermission(RoleEnum requiredRole) {
        User user = getLoggedInUserThrowable();
        if (user.getGameRole().getRole().getIndex() < requiredRole.getIndex()) {
            throw new InsufficientAuthenticationException("User not authorized");
        }
        return user;
    }

    public Map<String, String> checkJwtPayloadFromCookie() {
        Map<String, String> map = new HashMap<>();
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new InsufficientAuthenticationException("User not authorized");
        } else {
            map.put(
                    "getName()",
                    SecurityContextHolder.getContext().getAuthentication().getName()
            );
            map.put(
                    "getPrincipal()",
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
            );
            map.put(
                    "getAuthorities()",
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString()
            );
            map.put(
                    "getDetails()",
                    SecurityContextHolder.getContext().getAuthentication().getDetails().toString()
            );
        }
        return map;
    }

}
