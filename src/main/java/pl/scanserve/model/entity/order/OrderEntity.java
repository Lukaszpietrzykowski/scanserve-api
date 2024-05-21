package pl.scanserve.model.entity.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.scanserve.model.dto.order.OrderDTO;
import pl.scanserve.model.dto.order.OrderStatus;
import pl.scanserve.model.entity.menuitem.MenuItemEntity;
import pl.scanserve.model.entity.table.TableEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "scanserve_order")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableEntity table;

    private boolean paid;

    private BigDecimal orderTotalAmount;

    @Enumerated
    private OrderStatus status;

    private LocalDate orderTime;

    @ManyToMany
    @JoinTable(
            name = "order_menuitem",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "menuitem_id")
    )
    private List<MenuItemEntity> orderedMenuItems;


    public static OrderDTO toDTO(OrderEntity orderEntity) {
        return OrderDTO.builder()
                .tableId(orderEntity.getTable().getId())
                .orderId(orderEntity.getId())
                .totalPrice(orderEntity.getOrderTotalAmount())
                .status(orderEntity.getStatus())
                .build();
    }
}
