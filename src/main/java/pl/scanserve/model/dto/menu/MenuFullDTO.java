package pl.scanserve.model.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.scanserve.model.dto.category.CategoryFullDTO;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuFullDTO {

    private String displayName;
    private List<CategoryFullDTO> categories = new ArrayList<>();
}
