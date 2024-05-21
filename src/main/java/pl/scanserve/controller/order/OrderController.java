package pl.scanserve.controller.order;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.scanserve.model.dto.order.OrderCreateDTO;
import pl.scanserve.model.dto.order.OrderDTO;
import pl.scanserve.model.dto.order.OrderUpdateDTO;
import pl.scanserve.service.order.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void addOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        orderService.createOrder(orderCreateDTO);
    }

    @PutMapping
    public void updateOrderStatus(@RequestBody OrderUpdateDTO orderUpdateDTO) {
        orderService.updateOrderStatus(orderUpdateDTO);
    }

    @GetMapping
    public List<OrderDTO> getOrders() {
        return orderService.getOrders();
    }
}
