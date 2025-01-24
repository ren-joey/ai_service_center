//package com.asc.interceptor;
//
//import com.asc.security.JwtUtil;
//import com.asc.service.AuthService;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.util.ArrayList;
//
//import static com.asc.constant.Text.Env.JWT_KEY_NAME;
//
//@Component
//@RequiredArgsConstructor
//public class AuthenticateInterceptor implements HandlerInterceptor {
//    private final JwtUtil jwtUtil;
//    private final AuthService authService;
//
//    @Override
//    public boolean preHandle(
//            @NotNull HttpServletRequest request,
//            @NotNull HttpServletResponse response,
//            @NotNull Object handler) {
//
//        String jwt = extractJwtFromCookie(request);
//        String username;
//
//        if (jwt != null) {
//            try {
//                username = jwtUtil.extractUsername(jwt);
//                if (
//                    (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
//                    && jwtUtil.isTokenValid(jwt)
//                ) {
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                    return true;
//                } else throw new AuthenticationCredentialsNotFoundException("Invalid token provided");
//            } catch (Exception e) {
//                throw new AuthenticationCredentialsNotFoundException("Invalid token provided");
//            }
//        } else throw new AuthenticationCredentialsNotFoundException("Not authorized operation");
//    }
//
//    private String extractJwtFromHeader(HttpServletRequest request) {
//        String authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            return authorizationHeader.substring(7);
//        }
//        return null;
//    }
//
//    private String extractJwtFromCookie(HttpServletRequest request) {
//        if (request.getCookies() != null) {
//            for (Cookie cookie : request.getCookies()) {
//                if (cookie.getName().equals(System.getProperty(JWT_KEY_NAME))) {
//                    return cookie.getValue();
//                }
//            }
//        }
//        return null;
//    }
//
//    private void setNullAuthenticationToken() {
//        SecurityContextHolder.getContext().setAuthentication(
//                new UsernamePasswordAuthenticationToken(null, null, null)
//        );
//    }
//}
