package lab5.ownerMicroservice.mapper;

import jakarta.validation.constraints.NotNull;
import lab5.ownerMicroservice.dto.OwnerDto;
import lab5.ownerMicroservice.entity.Owner;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OwnerMapper {
    public OwnerDto toDto(@NotNull Owner owner) {
        return new OwnerDto(owner.getId(), owner.getName(), owner.getBirthDate(), owner.getUserId());
    }
}
