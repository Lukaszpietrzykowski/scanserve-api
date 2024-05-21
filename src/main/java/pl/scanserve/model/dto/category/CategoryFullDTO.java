package pl.scanserve.model.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.scanserve.model.dto.menuitem.MenuItemMobileDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryFullDTO {

    private Long categoryId;
    private String categoryName;
    private List<MenuItemMobileDTO> menuItems = new ArrayList<>();
}
