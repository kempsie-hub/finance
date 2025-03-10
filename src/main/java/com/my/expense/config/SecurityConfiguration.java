package com.my.expense.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",configuration);
        return urlBasedCorsConfigurationSource;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((AbstractHttpConfigurer::disable))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
//                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST,"/api/expense-types/**").hasRole("ADMIN");
//                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.PUT,"/api/expense-types/**").hasRole("ADMIN");
//                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE,"/api/expense-types/**").hasRole("ADMIN");
                    //authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET,"/v1/auth/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/v1/auth").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST,"/v1/auth/login").permitAll();
                    authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

}