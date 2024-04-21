package pl.scanserve.controller.category;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.scanserve.model.dto.category.CategoryCreateDTO;
import pl.scanserve.model.dto.category.CategoryDTO;
import pl.scanserve.service.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public List<CategoryDTO> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{categoryId}")
    public CategoryDTO getCategory(@PathVariable Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @PostMapping()
    public void addCategory(@RequestBody CategoryCreateDTO categoryCreate) {
        categoryService.addNewCategory(categoryCreate);
    }

    @PutMapping()
    public void updateCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(categoryDTO);
    }

    @DeleteMapping("/{categoryId}")
    public void removeCategory(@PathVariable Long categoryId) {
        categoryService.removeCategory(categoryId);
    }
}
