package pl.scanserve.service.category;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.scanserve.model.dto.category.CategoryCreateDTO;
import pl.scanserve.model.dto.category.CategoryDTO;
import pl.scanserve.model.entity.category.CategoryEntity;
import pl.scanserve.repository.category.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryEntity::toDTO)
                .toList();
    }

    public void addNewCategory(CategoryCreateDTO categoryCreateRequest) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryName(categoryCreateRequest.getCategoryName())
                .displayName(categoryCreateRequest.getDisplayName())
                .active(categoryCreateRequest.isActive())
                .build();
        categoryRepository.save(categoryEntity);
    }

    public void removeCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public void updateCategory(CategoryDTO categoryDTO) {
        CategoryEntity category = categoryRepository.getReferenceById(categoryDTO.getId());
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDisplayName(categoryDTO.getDisplayName());
        category.setActive(categoryDTO.isActive());
        categoryRepository.save(category);
    }

    @Transactional
    public CategoryDTO getCategory(Long categoryId) {
        return CategoryEntity.toDTO(categoryRepository.getReferenceById(categoryId));
    }
}
