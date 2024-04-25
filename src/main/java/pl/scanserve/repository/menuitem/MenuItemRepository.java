package pl.scanserve.repository.menuitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.scanserve.model.entity.menuitem.MenuItemEntity;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long> {
}
