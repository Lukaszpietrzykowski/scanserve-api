package pl.scanserve.service.table;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.scanserve.model.dto.table.TableCreateDTO;
import pl.scanserve.model.dto.table.TableDTO;
import pl.scanserve.model.entity.table.TableEntity;
import pl.scanserve.repository.menu.MenuRepository;
import pl.scanserve.repository.table.TableRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TableService {

    private final TableRepository tableRepository;
    private final MenuRepository menuRepository;

    public List<TableDTO> getTables() {
        return tableRepository.findAll().stream()
                .map(TableEntity::toDTO)
                .toList();
    }

    public void createTable(TableCreateDTO tableCreate) {
        TableEntity tableEntity = TableEntity.builder()
                .name(tableCreate.getName())
                .seatingCapacity(tableCreate.getSeatingCapacity())
                .menu(menuRepository.getReferenceById(tableCreate.getMenuId()))
                .build();
        tableRepository.save(tableEntity);
    }

    public void removeTable(Long tableId) {
        tableRepository.deleteById(tableId);
    }

    public void updateTable(TableDTO tableDTO) {
        TableEntity tableEntity = tableRepository.getReferenceById(tableDTO.getId());
        tableEntity.setName(tableDTO.getName());
        tableEntity.setSeatingCapacity(tableDTO.getSeatingCapacity());
        tableEntity.setMenu(menuRepository.getReferenceById(tableDTO.getMenuId()));
        tableRepository.save(tableEntity);
    }
}
