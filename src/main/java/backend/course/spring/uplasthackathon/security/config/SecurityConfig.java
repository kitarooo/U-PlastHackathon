package backend.course.spring.uplasthackathon.security.config;

import backend.course.spring.uplasthackathon.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfiguration {
    private final AuthenticationProvider authProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public String[] PERMIT_ALL = {
            "/api/v1/auth/**",
            "/swagger*/**",
            "/swagger-ui/**",
            "/backend/swagger-ui.html",
            "/documentation/**",
            "/v3/api-docs/**"
    };

    public String[] ADMIN = {
            "/api/v1/employees/**",
    };

    public String[] USER = {

    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .exceptionHandling(c -> c.authenticationEntryPoint((req, res, e) -> {
                    e.printStackTrace();
                    jwtAuthenticationEntryPoint.commence(req,res,e);
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(USER).hasRole("USER")
                        .requestMatchers(ADMIN).hasRole("ADMIN")
                        .requestMatchers(PERMIT_ALL).permitAll().anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
