package lab2.com.controller;

import lab2.com.data.models.Breed;
import lab2.com.data.models.Color;
import lab2.com.dto.KittyDto;
import lab2.com.service.KittyService;
import lab2.com.dto.OwnerDto;

import java.time.LocalDate;
import java.util.List;

public class KittyController {
    private final KittyService kittyService;

    public KittyController(KittyService kittyService) {
        this.kittyService = kittyService;
    }
    public Long createKitty(String name, LocalDate birthDay, Breed breed, Color color) {
        return kittyService.addKitty(name, birthDay, breed, color);
    }

    public void deleteKitty(Long id) {
        kittyService.deleteKittyById(id);
    }
    public OwnerDto getOwnerOfKitty(Long kittyId) {
        return kittyService.getOwnerOfKitty(kittyId);
    }

    public KittyDto getKittyById(Long id) {
        return kittyService.getKittyById(id);
    }

    public List<KittyDto> getAllKitties() {
        return kittyService.getAllKitties();
    }

    public void makeFriends(Long kittyId1, Long kittyId2) {
        kittyService.makeFriends(kittyId1, kittyId2);
    }

    public void unfriendKitties(Long kittyId1, Long kittyId2) {
        kittyService.unfriendKitties(kittyId1, kittyId2);
    }
}
