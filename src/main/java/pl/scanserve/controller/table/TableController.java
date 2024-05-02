package pl.scanserve.controller.table;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.scanserve.model.dto.table.TableCreateDTO;
import pl.scanserve.model.dto.table.TableDTO;
import pl.scanserve.model.dto.table.TableFullDTO;
import pl.scanserve.service.table.TableService;

import java.util.List;

@RestController
@RequestMapping("/tables")
@AllArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping
    public List<TableDTO> getTables() {
        return tableService.getTables();
    }

    @GetMapping("/{tableId}")
    public TableFullDTO getTableFullInfo(@PathVariable Long tableId) {
        return tableService.getTableFullInfo(tableId);
    }

    @PostMapping
    public void createTable(@RequestBody TableCreateDTO tableCreate) {
        tableService.createTable(tableCreate);
    }

    @PutMapping
    public void updateTable(@RequestBody TableDTO tableDTO) {
        tableService.updateTable(tableDTO);
    }

    @DeleteMapping("{tableId}")
    public void removeTable(@PathVariable Long tableId) {
        tableService.removeTable(tableId);
    }
}
