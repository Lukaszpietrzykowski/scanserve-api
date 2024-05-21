package pl.scanserve.model.entity.menuitem;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.scanserve.model.dto.menuitem.MenuItemDTO;
import pl.scanserve.model.dto.menuitem.MenuItemMobileDTO;
import pl.scanserve.model.entity.category.CategoryEntity;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "menu_item")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] itemImage;


    public static MenuItemDTO toDTO(MenuItemEntity menuItem) {
        return MenuItemDTO.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .price(menuItem.getPrice())
                .description(menuItem.getDescription())
                .categoryId(menuItem.getCategory().getId())
                .categoryName(menuItem.getCategory().getCategoryName())
                .image(menuItem.getItemImage())
                .build();

    }

    public static MenuItemMobileDTO toMobileDTO(MenuItemEntity menuItemEntity) {
        return MenuItemMobileDTO.builder()
                .menuItemId(menuItemEntity.getId())
                .name(menuItemEntity.getName())
                .description(menuItemEntity.getDescription())
                .price(menuItemEntity.getPrice())
                .image(menuItemEntity.getItemImage())
                .build();
    }

    public static List<MenuItemMobileDTO> toMobileDTOs(List<MenuItemEntity> menuItemEntities) {
        return menuItemEntities.stream()
                .map(MenuItemEntity::toMobileDTO)
                .toList();
    }
}
