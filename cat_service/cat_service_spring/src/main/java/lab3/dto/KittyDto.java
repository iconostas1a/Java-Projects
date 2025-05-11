package lab3.dto;

import lab3.models.Breed;
import lab3.models.Color;

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


