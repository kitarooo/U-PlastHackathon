package backend.course.spring.uplasthackathon.service;

import backend.course.spring.uplasthackathon.dto.request.EmployeeUpdateRequest;
import backend.course.spring.uplasthackathon.dto.request.RegistrationRequest;
import backend.course.spring.uplasthackathon.dto.response.EmployeeDescription;
import backend.course.spring.uplasthackathon.entity.User;
import backend.course.spring.uplasthackathon.entity.enums.Role;
import backend.course.spring.uplasthackathon.exception.NotFoundException;
import backend.course.spring.uplasthackathon.exception.UserAlreadyExistException;
import backend.course.spring.uplasthackathon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String employeeRegister(RegistrationRequest request) {
        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("Работник с email: " + request.getEmail() + " уже существует!");
        }

        if (userRepository.findUserByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("Работник с username: " + request.getUsername() + " уже существует!");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.EMPLOYEE)
                .build();
        userRepository.save(user);

        return "Регистрация работника прошла успешно!";
    }

    public String updateEmployeeById(Long id, EmployeeUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь с таким id:" + id + " не найден!"));

        user.setEmail(request.getEmail());
        user.setFirstname(request.getFirstname());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());

        userRepository.save(user);

        return "Данные работника успешно обновлены!";
    }

    public String deleteEmployeeByUsername(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NotFoundException("Пользователь с таким username : " + username + " не найден!"));
        userRepository.delete(user);

        return "Работник успешно удален!";
    }

    public EmployeeDescription getEmployeeByUsername(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NotFoundException("Пользоватеь с таким username : " + username + " не найден!"));

        return EmployeeDescription.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getFirstname())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public List<String > getAllEmployees() {
        return userRepository.findAll()
                .stream()
                .filter(a -> a.getRole() == Role.EMPLOYEE)
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    public List<User> getAllWithFullDesc() {
        return userRepository.findAll()
                .stream()
                .filter(a -> a.getRole() == Role.EMPLOYEE)
                .collect(Collectors.toList());
    }
}
