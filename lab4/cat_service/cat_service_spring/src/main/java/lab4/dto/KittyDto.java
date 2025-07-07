package lab4.dto;

import lab4.models.Breed;
import lab4.models.Color;

import java.time.LocalDate;
import java.util.List;

public record KittyDto(Long id,
                       String name,
                       LocalDate birthDate,
                       Breed breed,
                       Color color,
                       Long ownerId,
                       List<Long> friendsId) {
}
