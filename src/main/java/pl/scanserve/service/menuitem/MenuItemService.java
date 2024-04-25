package pl.scanserve.service.menuitem;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.scanserve.model.dto.menuitem.MenuItemCreateDTO;
import pl.scanserve.model.dto.menuitem.MenuItemDTO;
import pl.scanserve.model.entity.menuitem.MenuItemEntity;
import pl.scanserve.repository.category.CategoryRepository;
import pl.scanserve.repository.menuitem.MenuItemRepository;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;

    public List<MenuItemDTO> getMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(MenuItemEntity::toDTO)
                .toList();
    }

    public void createMenuItem(MenuItemCreateDTO menuItem, MultipartFile image) throws IOException {
        MenuItemEntity menuItemEntity = MenuItemEntity.builder()
                .name(menuItem.getName())
                .price(menuItem.getPrice())
                .description(menuItem.getDescription())
                .itemImage(image.getBytes())
                .category(categoryRepository.getReferenceById(menuItem.getCategoryId()))
                .build();
        menuItemRepository.save(menuItemEntity);
    }

    public void removeMenuItem(Long menuItemId) {
        menuItemRepository.deleteById(menuItemId);
    }

    @Transactional
    public void updateMenuItem(MenuItemDTO menuItem, MultipartFile image) throws IOException {
        MenuItemEntity menuItemEntity = menuItemRepository.getReferenceById(menuItem.getId());
        menuItemEntity.setName(menuItem.getName());
        menuItemEntity.setDescription(menuItem.getDescription());
        menuItemEntity.setPrice(menuItem.getPrice());
        menuItemEntity.setItemImage(image.getBytes());
        menuItemEntity.setCategory(categoryRepository.getReferenceById(menuItem.getCategoryId()));
        menuItemRepository.save(menuItemEntity);
    }
}
