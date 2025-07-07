package lab2.com.service;

import lab2.com.data.dao.KittyDao;
import lab2.com.data.dao.OwnerDao;
import lab2.com.data.entities.Kitty;
import lab2.com.data.entities.Owner;
import lab2.com.data.models.Breed;
import lab2.com.data.models.Color;
import lab2.com.dto.KittyDto;
import lab2.com.dto.OwnerDto;
import lab2.com.mapper.OwnerMapper;
import lab2.com.mapper.KittyMapper;

import java.time.LocalDate;
import java.util.List;

public class KittyService {
    private final KittyDao kittyDao;

    public KittyService(KittyDao kittyDao) {
        this.kittyDao = kittyDao;
    }
    public Long addKitty(String name, LocalDate birthday, Breed breed, Color color) {
        Kitty kitty = new Kitty();
        kitty.setName(name);
        kitty.setBirthDate(birthday);
        kitty.setBreed(breed);
        kitty.setColor(color);

        kittyDao.addKitty(kitty);
        return kitty.getId();
    }

    public OwnerDto getOwnerOfKitty(Long kittyId) {
        return OwnerMapper.toDto(kittyDao.getOwner(kittyId));
    }

    public KittyDto getKittyById(Long kittyId) {
        return KittyMapper.toDto(kittyDao.getKittyById(kittyId));
    }
    public void makeFriends(Long kittyId1, Long kittyId2) {
        Kitty kitty1 = kittyDao.getKittyById(kittyId1);
        Kitty kitty2 = kittyDao.getKittyById(kittyId2);
        kitty1.addFriend(kitty2);
        kitty2.addFriend(kitty1);
        kittyDao.updateKitty(kitty1);
    }

    public void unfriendKitties(Long kittyId1, Long kittyId2) {
        Kitty kitty1 = kittyDao.getKittyById(kittyId1);
        Kitty kitty2 = kittyDao.getKittyById(kittyId2);
        kitty1.removeFriend(kitty2);
        kitty2.removeFriend(kitty1);
        kittyDao.updateKitty(kitty1);
    }
    public List<KittyDto> getAllKitties() {
        return kittyDao.getAllKitties().stream()
                .map(KittyMapper::toDto)
                .toList();
    }

    public void deleteKittyById(Long kittyId) {
        kittyDao.deleteKittyById(kittyId);
    }
}
