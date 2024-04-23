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
public class MenuItemDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String categoryName;
    private Long categoryId;
    private byte[] image;
}
