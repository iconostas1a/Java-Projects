package lab5.kittyMicroservice.mapper;

import lab5.kittyMicroservice.dto.KittyDto;
import lab5.kittyMicroservice.entity.Kitty;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class KittyMapper {
    public KittyDto toDto(Kitty kitty) {
        List<Long> friendsId = kitty.getFriendsId() == null
                ? List.of()
                : kitty.getFriendsId();
        return new KittyDto(kitty.getId(), kitty.getName(), kitty.getBirthDate(), kitty.getBreed(),
                kitty.getColor(), kitty.getOwnerId(), friendsId);
    }
}
