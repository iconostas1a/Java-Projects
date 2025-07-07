package lab2.com.mapper;

import lab2.com.data.entities.Owner;
import lab2.com.dto.OwnerDto;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
@ExtensionMethod(KittyMapper.class)
public class OwnerMapper {
    public OwnerDto toDto(Owner owner) {
        List<Long> kittiesDto = new ArrayList<>();
        owner.getKitties().forEach(kitty -> kittiesDto.add(kitty.getId()));
        return new OwnerDto(owner.getId(), owner.getName(), owner.getBirthDate(), kittiesDto);
    }
}
