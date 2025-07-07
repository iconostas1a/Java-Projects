package lab4;

import lab4.data.entity.Kitty;
import lab4.models.Color;
import org.springframework.data.jpa.domain.Specification;

public class KittySpecifications {

    public static Specification<Kitty> hasName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Kitty> hasBreed(String breed) {
        return (root, query, cb) -> cb.equal(root.get("breed"), breed);
    }

    public static Specification<Kitty> hasColor(Color color) {
        return (root, query, cb) -> cb.equal(root.get("color"), color);
    }
}
