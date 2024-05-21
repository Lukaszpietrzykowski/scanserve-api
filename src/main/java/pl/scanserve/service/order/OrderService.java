package pl.scanserve.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.scanserve.model.dto.order.OrderCreateDTO;
import pl.scanserve.model.dto.order.OrderDTO;
import pl.scanserve.model.dto.order.OrderStatus;
import pl.scanserve.model.dto.order.OrderUpdateDTO;
import pl.scanserve.model.entity.menuitem.MenuItemEntity;
import pl.scanserve.model.entity.order.OrderEntity;
import pl.scanserve.repository.menuitem.MenuItemRepository;
import pl.scanserve.repository.order.OrderRepository;
import pl.scanserve.repository.table.TableRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final TableRepository tableRepository;

    public void createOrder(OrderCreateDTO orderCreateDTO) {
        List<MenuItemEntity> menuItems = orderCreateDTO.getMenuItems().stream()
                .map(menuItemOrder -> menuItemRepository.findById(menuItemOrder.getMenuItemId()).orElse(null))
                .toList();

        OrderEntity orderEntity = OrderEntity.builder()
                .table(tableRepository.getReferenceById(orderCreateDTO.getTableId()))
                .orderTotalAmount(calculateOrderTotalAmount(menuItems))
                .status(OrderStatus.NEW)
                .orderTime(LocalDate.now())
                .orderedMenuItems(menuItems)
                .build();
        orderRepository.save(orderEntity);
    }


    private BigDecimal calculateOrderTotalAmount(List<MenuItemEntity> menuItems) {
        return menuItems.stream()
                .map(MenuItemEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void updateOrderStatus(OrderUpdateDTO orderUpdateDTO) {
        OrderEntity orderEntity = orderRepository.getReferenceById(orderUpdateDTO.getOrderId());
        orderEntity.setStatus(orderUpdateDTO.getOrderStatus());
        orderRepository.save(orderEntity);
    }

    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream()
                .map(OrderEntity::toDTO)
                .filter(orderDTO -> orderDTO.getStatus() != OrderStatus.COMPLETED)
                .toList();
    }
}
