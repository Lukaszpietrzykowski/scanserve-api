package pl.scanserve.service.menu;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.scanserve.model.dto.menu.MenuCreateDTO;
import pl.scanserve.model.dto.menu.MenuDTO;
import pl.scanserve.model.entity.menu.MenuEntity;
import pl.scanserve.repository.category.CategoryRepository;
import pl.scanserve.repository.menu.MenuRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public List<MenuDTO> getMenus() {
        return menuRepository.findAll().stream()
                .map(MenuEntity::toDTO)
                .collect(Collectors.toList());
    }

    public void createMenu(MenuCreateDTO menuCreateDTO) {
        MenuEntity menuEntity = MenuEntity.builder()
                .name(menuCreateDTO.getName())
                .displayName(menuCreateDTO.getDisplayName())
                .categories(menuCreateDTO.getCategoryIds().stream()
                        .map(categoryRepository::getReferenceById)
                        .toList())
                .build();
        menuRepository.save(menuEntity);
    }

    public void removeMenu(Long menuId) {
        menuRepository.deleteById(menuId);
    }

    public void updateMenu(MenuDTO menu) {
        MenuEntity menuEntity = menuRepository.getReferenceById(menu.getId());
        menuEntity.setName(menu.getName());
        menuEntity.setDisplayName(menu.getDisplayName());
        menuEntity.setCategories(menu.getCategoryIds().stream()
                .map(categoryRepository::getReferenceById)
                .toList());
        menuRepository.save(menuEntity);
    }
}
