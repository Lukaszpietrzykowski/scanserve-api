package pl.scanserve.model.entity.menu;

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
import pl.scanserve.model.dto.menu.MenuDTO;
import pl.scanserve.model.dto.menu.MenuFullDTO;
import pl.scanserve.model.entity.category.CategoryEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String displayName;
    private boolean active;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<CategoryEntity> categories = new ArrayList<>();


    public static MenuDTO toDTO(MenuEntity menuEntity) {
        return MenuDTO.builder()
                .id(menuEntity.getId())
                .name(menuEntity.getName())
                .displayName(menuEntity.getDisplayName())
                .categoryIds(menuEntity.getCategories().stream()
                        .map(CategoryEntity::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public static MenuFullDTO toFullDTO(MenuEntity menuEntity) {
        return MenuFullDTO.builder()
                .displayName(menuEntity.getDisplayName())
                .categories(CategoryEntity.toFullDTOs(menuEntity.getCategories()))
                .build();
    }
}