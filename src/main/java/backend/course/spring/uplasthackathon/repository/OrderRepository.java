package backend.course.spring.uplasthackathon.repository;

import backend.course.spring.uplasthackathon.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderName(String orderName);
    @Query(value = "SELECT o.* FROM orders o WHERE o.user_id = :userId", nativeQuery = true)
    List<Order> getOrderByUserId(@Param("userId") Long id);
}
