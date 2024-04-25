package pl.scanserve.model.entity.category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.scanserve.model.dto.category.CategoryDTO;
import pl.scanserve.model.entity.menuitem.MenuItemEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String categoryName;
    private String displayName;
    private boolean active;

    @OneToMany
    @JoinColumn(name = "menu_item_id")
    private List<MenuItemEntity> menuItems = new ArrayList<>();

    public static CategoryDTO toDTO(CategoryEntity category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .displayName(category.getDisplayName())
                .active(category.isActive())
                .build();
    }
}
