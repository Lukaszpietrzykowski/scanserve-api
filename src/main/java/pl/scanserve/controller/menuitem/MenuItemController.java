package pl.scanserve.controller.menuitem;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.scanserve.model.dto.menuitem.MenuItemCreateDTO;
import pl.scanserve.model.dto.menuitem.MenuItemDTO;
import pl.scanserve.service.menuitem.MenuItemService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/menu-items")
@AllArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping
    public List<MenuItemDTO> getMenuItems() {
        return menuItemService.getMenuItems();
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateMenuItem(@RequestPart("menuItem") MenuItemDTO menuItem, @RequestPart("image") MultipartFile image) throws IOException {
        menuItemService.updateMenuItem(menuItem, image);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createMenuItem(@RequestPart("menuItem") MenuItemCreateDTO menuItem, @RequestPart("image") MultipartFile image) throws IOException {
        menuItemService.createMenuItem(menuItem, image);
    }

    @DeleteMapping("/{menuItemId}")
    public void removeMenuItem(@PathVariable Long menuItemId) {
        menuItemService.removeMenuItem(menuItemId);

    }
}
