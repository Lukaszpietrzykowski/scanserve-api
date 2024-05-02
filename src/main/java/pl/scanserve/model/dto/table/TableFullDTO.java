package pl.scanserve.model.dto.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.scanserve.model.dto.menu.MenuFullDTO;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableFullDTO {

    public String name;
    public MenuFullDTO menu;
}
