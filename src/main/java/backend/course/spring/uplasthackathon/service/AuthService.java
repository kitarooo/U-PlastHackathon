package backend.course.spring.uplasthackathon.service;

import backend.course.spring.uplasthackathon.dto.request.LoginRequest;
import backend.course.spring.uplasthackathon.dto.request.RegistrationRequest;
import backend.course.spring.uplasthackathon.dto.response.AuthenticationResponse;
import backend.course.spring.uplasthackathon.entity.User;
import backend.course.spring.uplasthackathon.entity.enums.Role;
import backend.course.spring.uplasthackathon.exception.IncorrectDataException;
import backend.course.spring.uplasthackathon.exception.NotFoundException;
import backend.course.spring.uplasthackathon.exception.UserAlreadyExistException;
import backend.course.spring.uplasthackathon.repository.UserRepository;
import backend.course.spring.uplasthackathon.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String userRegistration(RegistrationRequest request) {
        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("Пользователь с email: " + request.getEmail() + " уже существует!");
        }

        if (userRepository.findUserByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("Пользователь с username: " + request.getUsername() + " уже существует!");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);

        return "Регистрация прошла успешно!";
    }

    public AuthenticationResponse userLogin(LoginRequest request) {
        User user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("Данные введены неправильно, повторите попытку!"));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword()) && user.getUsername().equals(request.getUsername())) {
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new IncorrectDataException("Данные введены неправильно, повторите попытку!");
        }
    }
}
