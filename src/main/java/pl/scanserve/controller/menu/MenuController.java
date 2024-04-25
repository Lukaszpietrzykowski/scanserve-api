package pl.scanserve.controller.menu;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.scanserve.model.dto.menu.MenuCreateDTO;
import pl.scanserve.model.dto.menu.MenuDTO;
import pl.scanserve.service.menu.MenuService;

import java.util.List;

@RestController
@RequestMapping("/menus")
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public List<MenuDTO> getMenus() {
        return menuService.getMenus();
    }

    @PostMapping
    public void createMenu(@RequestBody MenuCreateDTO menuCreateDTO) {
        menuService.createMenu(menuCreateDTO);
    }

    @PutMapping
    public void updateMenu(@RequestBody MenuDTO menu) {
        menuService.updateMenu(menu);
    }

    @DeleteMapping("/{menuId}")
    public void removeMenu(@PathVariable Long menuId) {
        menuService.removeMenu(menuId);
    }
}
