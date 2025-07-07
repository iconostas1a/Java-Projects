package lab2.com.mapper;

import lab2.com.data.entities.Kitty;
import lab2.com.dto.KittyDto;
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
