package backend.course.spring.uplasthackathon.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String orderName;
    LocalDate createdDate;
    String userPhoneNumber;
    Long userId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    Catalog catalog;

}
