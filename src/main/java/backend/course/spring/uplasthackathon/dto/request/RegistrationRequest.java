package backend.course.spring.uplasthackathon.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequest {
    String username;
    String email;
    String firstname;
    String lastname;
    String password;
}
