package lab4.repository;

import lab4.data.entity.Kitty;
import lab4.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KittyRepository extends JpaRepository<Kitty, Long>, JpaSpecificationExecutor<Kitty> {

    List<Kitty> findTop5ByColor(Color color);
}
