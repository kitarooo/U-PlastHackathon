package backend.course.spring.uplasthackathon.dto.request;

import backend.course.spring.uplasthackathon.entity.Catalog;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    String orderName;
    LocalDate createdDate;
    String userPhoneNumber;
    Catalog catalog;
}
