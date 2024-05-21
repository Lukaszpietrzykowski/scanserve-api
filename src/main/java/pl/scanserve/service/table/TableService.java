package pl.scanserve.service.table;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.scanserve.model.dto.table.TableCreateDTO;
import pl.scanserve.model.dto.table.TableDTO;
import pl.scanserve.model.dto.table.TableFullDTO;
import pl.scanserve.model.entity.menu.MenuEntity;
import pl.scanserve.model.entity.table.TableEntity;
import pl.scanserve.repository.menu.MenuRepository;
import pl.scanserve.repository.table.TableRepository;
import pl.scanserve.service.qrcode.QRCodeService;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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

    public void createTable(TableCreateDTO tableCreate) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        TableEntity tableEntity = TableEntity.builder()
                .name(tableCreate.getName())
                .seatingCapacity(tableCreate.getSeatingCapacity())
                .menu(menuRepository.getReferenceById(tableCreate.getMenuId()))
                .build();
        TableEntity save = tableRepository.save(tableEntity);
        ImageIO.write(QRCodeService.generateQRCodeImage(String.format("http://192.168.0.178:5173/mobile/%d/menu", save.getId())), "png", baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        save.setQrCode(is.readAllBytes());
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

    @Transactional
    public TableFullDTO getTableFullInfo(Long tableId) {
        TableEntity tableEntity = tableRepository.getReferenceById(tableId);
        return TableFullDTO.builder()
                .name(tableEntity.getName())
                .menu(MenuEntity.toFullDTO(tableEntity.getMenu()))
                .build();
    }
}
