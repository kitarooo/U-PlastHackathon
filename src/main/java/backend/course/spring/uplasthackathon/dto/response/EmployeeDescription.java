package backend.course.spring.uplasthackathon.dto.response;

import backend.course.spring.uplasthackathon.entity.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDescription {
    Long id;
    String username;
    String email;
    String firstname;
    String lastname;
    String password;
    Role role;
}
