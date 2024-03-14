package backend.course.spring.uplasthackathon.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CatalogResponse {
    Long id;
    String catalogName;
    String imageUrl;
    double price;
    String description;
    String features;
    Long userId;
}
