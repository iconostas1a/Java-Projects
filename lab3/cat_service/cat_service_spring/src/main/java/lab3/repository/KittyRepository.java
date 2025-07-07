package lab3.repository;

import lab3.data.entity.Kitty;
import lab3.models.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KittyRepository extends JpaRepository<Kitty, Long>, JpaSpecificationExecutor<Kitty> {

    List<Kitty> findTop5ByColor(Color color);
}
