package pl.scanserve.model.entity.table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.scanserve.model.dto.table.TableDTO;
import pl.scanserve.model.entity.menu.MenuEntity;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long seatingCapacity;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;

    public static TableDTO toDTO(TableEntity table) {
        return TableDTO.builder()
                .id(table.getId())
                .name(table.getName())
                .seatingCapacity(table.getSeatingCapacity())
                .menuId(table.getMenu().getId())
                .menuName(table.getMenu().getName())
                .build();
    }
}
