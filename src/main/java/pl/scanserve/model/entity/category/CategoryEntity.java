package pl.scanserve.model.entity.category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.scanserve.model.dto.category.CategoryDTO;
import pl.scanserve.model.dto.category.CategoryFullDTO;
import pl.scanserve.model.entity.menuitem.MenuItemEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
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

    @OneToMany(mappedBy = "category")
    private List<MenuItemEntity> menuItems = new ArrayList<>();

    public static CategoryDTO toDTO(CategoryEntity category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .displayName(category.getDisplayName())
                .active(category.isActive())
                .itemsInCategory(category.getMenuItems().size())
                .build();
    }

    public static CategoryFullDTO toFullDTO(CategoryEntity category) {
        return CategoryFullDTO.builder()
                .categoryId(category.getId())
                .categoryName(category.getDisplayName())
                .menuItems(MenuItemEntity.toMobileDTOs(category.getMenuItems()))
                .build();
    }

    public static List<CategoryFullDTO> toFullDTOs(List<CategoryEntity> categoryEntities) {
        return categoryEntities.stream()
                .map(CategoryEntity::toFullDTO)
                .toList();
    }
}
