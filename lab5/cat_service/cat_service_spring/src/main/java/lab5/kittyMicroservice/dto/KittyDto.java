package lab5.kittyMicroservice.dto;

import lab5.kittyMicroservice.models.Breed;
import lab5.kittyMicroservice.models.Color;

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