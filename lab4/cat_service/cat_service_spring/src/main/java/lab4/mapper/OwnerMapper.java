package lab4.mapper;

import jakarta.validation.constraints.NotNull;
import lab4.data.entity.Owner;
import lab4.dto.OwnerDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class OwnerMapper {
    public OwnerDto toDto(@NotNull Owner owner) {
        List<Long> kittiesDto = new ArrayList<>();
        owner.getKitties().forEach(kitty -> kittiesDto.add(kitty.getId()));
        return new OwnerDto(owner.getId(), owner.getName(), owner.getBirthDate(), kittiesDto);
    }
}