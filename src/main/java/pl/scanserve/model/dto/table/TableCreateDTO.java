package pl.scanserve.model.dto.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableCreateDTO {

    private String name;
    private Long seatingCapacity;
    private Long menuId;
}
