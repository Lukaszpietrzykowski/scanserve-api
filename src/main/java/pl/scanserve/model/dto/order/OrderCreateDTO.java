package pl.scanserve.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDTO {

    private List<MenuItemOrder> menuItems = new ArrayList<>();
    private Long tableId;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MenuItemOrder {
        private Long menuItemId;
        private Long amount;
    }
}

