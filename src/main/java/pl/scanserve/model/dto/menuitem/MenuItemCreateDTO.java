package pl.scanserve.model.dto.menuitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemCreateDTO {

    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId;
}
