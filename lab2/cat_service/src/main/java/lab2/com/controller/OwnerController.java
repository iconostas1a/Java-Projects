package lab2.com.controller;

import lab2.com.dto.OwnerDto;
import lab2.com.service.OwnerService;

import java.time.LocalDate;

public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
    public Long addOwner(String name, LocalDate birthDate) {
        return ownerService.addOwner(name, birthDate);
    }

    public void updateOwner(Long id, String name, LocalDate birthDate) {
        ownerService.updateOwner(id, name, birthDate);
    }
    public void addKittyToOwner(Long kittyId, Long ownerId) {
        ownerService.addKittyToOwner(kittyId, ownerId);
    }

    public void removeOwner(Long id) {
        ownerService.deleteOwner(id);
    }

    public OwnerDto getOwnerById(Long id) {
        return ownerService.getOwnerById(id);
    }
}
