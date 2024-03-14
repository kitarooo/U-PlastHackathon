package backend.course.spring.uplasthackathon.controller;

import backend.course.spring.uplasthackathon.dto.request.OrderRequest;
import backend.course.spring.uplasthackathon.entity.Order;
import backend.course.spring.uplasthackathon.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public String createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping("/all")
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("{id}")
    public Order getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping("/my-orders")
    public List<Order> getOrdersByUser() {
        return orderService.getOrderByUserId();
    }
}
