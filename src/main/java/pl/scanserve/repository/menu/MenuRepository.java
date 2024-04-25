package pl.scanserve.repository.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.scanserve.model.entity.menu.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
}
