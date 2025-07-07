package lab3.mapper;

import lab3.data.entity.Owner;
import lab3.dto.OwnerDto;
import lombok.experimental.UtilityClass;

import javax.validation.constraints.NotNull;
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