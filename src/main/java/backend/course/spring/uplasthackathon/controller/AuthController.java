package backend.course.spring.uplasthackathon.controller;

import backend.course.spring.uplasthackathon.dto.request.LoginRequest;
import backend.course.spring.uplasthackathon.dto.request.RegistrationRequest;
import backend.course.spring.uplasthackathon.dto.response.AuthenticationResponse;
import backend.course.spring.uplasthackathon.exception.handler.ExceptionResponse;
import backend.course.spring.uplasthackathon.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Регистрация для клиента", description = "Ендпоинт для регистрации клиента!",
        responses = {
            @ApiResponse(
                    content = @Content(mediaType = "string"),
                    responseCode = "200", description = "Good"),
            @ApiResponse(
                    content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ExceptionResponse.class)),
                    responseCode = "400", description = "User already exist exception!"
            )
        })
    public String userRegistration(@RequestBody RegistrationRequest request) {
        return authService.userRegistration(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Авторизация для всех пользователей", description = "Ендпоинт для авторизации и выдачи токенов!",
        responses = {
            @ApiResponse(
                    content = @Content(mediaType = "stringg"),
                    responseCode = "200", description = "Good"),
            @ApiResponse(
                    content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ExceptionResponse.class)),
                    responseCode = "400", description = "Incorrect Data Exception!"
            )
        })
    public AuthenticationResponse userLogin(@RequestBody LoginRequest request) {
        return authService.userLogin(request);
    }
}
