package lab2.com.dto;

import lab2.com.data.models.Breed;
import lab2.com.data.models.Color;

import java.time.LocalDate;
import java.util.List;

public record KittyDto(Long id,
                       String name,
                       LocalDate birthDay,
                       Breed breed,
                       Color colour,
                       Long ownerId,
                       List<Long> friendsId) {
}
