package backend.course.spring.uplasthackathon.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CatalogRequest {

    String catalogName;
    double price;
    String description;
    String features;

}
