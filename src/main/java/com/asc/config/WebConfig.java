//package com.asc.config;
//
//import com.asc.interceptor.AuthenticateInterceptor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@RequiredArgsConstructor
//public class WebConfig implements WebMvcConfigurer {
//    private static final String[] AUTHENTICATE_INTERCEPTOR_EXCLUDE_PATH = {
//            "/auth/**",
//            "/swagger-ui/**",
//            "/v3/api-docs/**"
//    };
//
//    private final AuthenticateInterceptor authenticateInterceptor;
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry
//                .addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods(
//                        HttpMethod.GET.name(),
//                        HttpMethod.POST.name(),
//                        HttpMethod.PUT.name(),
//                        HttpMethod.PATCH.name(),
//                        HttpMethod.DELETE.name(),
//                        HttpMethod.OPTIONS.name())
//                .allowedHeaders("*")
//                .maxAge(86400);
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry
//                .addInterceptor(authenticateInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns(AUTHENTICATE_INTERCEPTOR_EXCLUDE_PATH)
//                .order(Ordered.HIGHEST_PRECEDENCE);
//    }
//
////    @Override
////    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
////        configurer.enable();
////    }
//}
