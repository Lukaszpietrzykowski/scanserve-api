package pl.scanserve.model.dto.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableDTO {

    private Long id;
    private String name;
    private Long seatingCapacity;
    private String menuName;
    private Long menuId;
    private byte[] qrCode;
}
