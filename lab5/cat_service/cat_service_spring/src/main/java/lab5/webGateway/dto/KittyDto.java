package lab5.webGateway.dto;
import java.time.LocalDate;
import java.util.List;
import lab5.webGateway.Color;
import lab5.webGateway.Breed;

public record KittyDto(Long id,
                       String name,
                       LocalDate birthDate,
                       Breed breed,
                       Color color,
                       Long ownerId,
                       List<Long> friendsId) {
}
