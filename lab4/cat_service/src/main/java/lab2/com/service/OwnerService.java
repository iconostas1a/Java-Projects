package lab2.com.service;

import lab2.com.data.dao.KittyDao;
import lab2.com.data.dao.OwnerDao;
import lab2.com.data.entities.Kitty;
import lab2.com.data.entities.Owner;
import lab2.com.dto.OwnerDto;
import lab2.com.mapper.OwnerMapper;

import java.time.LocalDate;
import java.util.Collections;

public class OwnerService {
    private final OwnerDao ownerDao;
    private final KittyDao kittyDao;

    public OwnerService(OwnerDao ownerDao, KittyDao kittenDao) {
        this.ownerDao = ownerDao;
        this.kittyDao = kittenDao;
    }
    public Long addOwner(String name, LocalDate birthDate) {
        Owner owner = new Owner();
        owner.setName(name);
        owner.setBirthDate(birthDate);
        owner.setKitties(Collections.emptyList());
        ownerDao.addOwner(owner);

        return owner.getId();
    }
    public void updateOwner(Long id, String name, LocalDate birthDate) {
        Owner owner = new Owner();
        owner.setId(id);
        owner.setName(name);
        owner.setBirthDate(birthDate);

        ownerDao.updateOwner(owner);
    }
    public OwnerDto getOwnerById(Long id) {
        return OwnerMapper.toDto(ownerDao.getOwnerById(id));
    }
    public void addKittyToOwner(Long kittyId, Long ownerId) {
        Owner owner = ownerDao.getOwnerById(ownerId);
        Kitty kitty = kittyDao.getKittyById(kittyId);

        owner.getKitties().add(kitty);
        ownerDao.updateOwner(owner);
        kitty.setOwner(owner);
        kittyDao.updateKitty(kitty);
    }
    public void deleteOwner(Long ownerId) {
        ownerDao.deleteOwnerById(ownerId);
    }
}
