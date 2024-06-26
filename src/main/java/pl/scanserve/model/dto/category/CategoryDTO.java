package pl.scanserve.model.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private long id;
    private String categoryName;
    private String displayName;
    private boolean active;
    private int itemsInCategory;
}
