package lab5.kittyMicroservice.repository;

import lab5.kittyMicroservice.entity.Kitty;
import lab5.kittyMicroservice.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KittyRepository extends JpaRepository<Kitty, Long>, JpaSpecificationExecutor<Kitty> {

    List<Kitty> findTop5ByColor(Color color);
}
