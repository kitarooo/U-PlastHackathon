package backend.course.spring.uplasthackathon.service;

import backend.course.spring.uplasthackathon.dto.request.OrderRequest;
import backend.course.spring.uplasthackathon.entity.Order;
import backend.course.spring.uplasthackathon.entity.User;
import backend.course.spring.uplasthackathon.exception.NotFoundException;
import backend.course.spring.uplasthackathon.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public String createOrder(OrderRequest request) {
        User user = getAuthUser();

        Random random = new Random();
        Long token = random.nextLong(1000000, 9999999);
        String randomName = "Заказ №" + token.toString() + " оформлен!";
        String name = "Заказ №" + token.toString();

        Order order = Order.builder()
                .orderName(name)
                .createdDate(LocalDate.now())
                .catalog(request.getCatalog())
                .userPhoneNumber(user.getPhoneNumber())
                .userId(user.getId())
                .build();

        orderRepository.save(order);
        return randomName;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public List<Order> getOrderByUserId() {
        User user = getAuthUser();
        return orderRepository.getOrderByUserId(user.getId());
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Заказ не найден!"));
    }

    private User getAuthUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
