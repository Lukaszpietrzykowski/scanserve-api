package pl.scanserve.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.scanserve.model.entity.table.TableEntity;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {
}
