package backend.course.spring.uplasthackathon.controller;

import backend.course.spring.uplasthackathon.dto.request.EmployeeUpdateRequest;
import backend.course.spring.uplasthackathon.dto.request.RegistrationRequest;
import backend.course.spring.uplasthackathon.dto.response.EmployeeDescription;
import backend.course.spring.uplasthackathon.entity.User;
import backend.course.spring.uplasthackathon.exception.handler.ExceptionResponse;
import backend.course.spring.uplasthackathon.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Tag(name = "Admin and Employee endpoints", description = "CRUD with employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/create")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @Operation(summary = "Admin endpoint", description = "Для регистрации нового сотрудника!",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "string"),
                            responseCode = "200", description = "Good"),
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)),
                            responseCode = "400", description = "Employee already exist exception!"
                    )
            })
    public String employeeRegister(@RequestBody RegistrationRequest request) {
        return employeeService.employeeRegister(request);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Admin endpoint", description = "Для обновления личных данных сотрудника!",
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "string"),
                        responseCode = "200", description = "Good"),
                @ApiResponse(
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = ExceptionResponse.class)),
                        responseCode = "404", description = "Employee not found!"
            )
            })
    public String updateEmployeeInfo(@PathVariable Long id, @RequestBody EmployeeUpdateRequest request) {
        return employeeService.updateEmployeeById(id, request);
    }

    @DeleteMapping
    @Operation(summary = "Admin endpoint", description = "Для удаления сотрудника!",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "string"),
                            responseCode = "200", description = "Good"),
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)),
                            responseCode = "404", description = "Employee not found!"
                    )
            })
    public String deleteEmployeeByUsername(@RequestParam String username) {
        return employeeService.deleteEmployeeByUsername(username);
    }

    @GetMapping("/{username}")
    @Operation(summary = "Admin and Employee endpoint", description = "Для получения полной информации сотрудника!",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "string"),
                            responseCode = "200", description = "Good"),
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)),
                            responseCode = "404", description = "Employee not found!"
                    )
            })
    public EmployeeDescription getEmployeeByUsername(@PathVariable String username) {
        return employeeService.getEmployeeByUsername(username);
    }

    @GetMapping("/all")
    @Operation(summary = "Admin endpoint", description = "Для получения username всех сотрудников!")
    public List<String> getAll() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getAll")
    @Operation(summary = "Admin endpoint", description = "Для получения всех сотрудников с полными данными!")
    public List<User> getAllWithFullDesc() {
        return employeeService.getAllWithFullDesc();
    }
}
