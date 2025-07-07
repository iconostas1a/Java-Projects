package lab3.mapper;

import lab3.data.entity.Kitty;
import lab3.dto.KittyDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class KittyMapper {
    public KittyDto toDto(Kitty kitty) {
        List<Long> friendsId = new ArrayList<>();
        kitty.getFriends().forEach(k -> friendsId.add(k.getId()));
        return new KittyDto(kitty.getId(), kitty.getName(), kitty.getBirthDate(), kitty.getBreed(),
                kitty.getColor(), kitty.getOwner().getId(), friendsId);
    }
}
