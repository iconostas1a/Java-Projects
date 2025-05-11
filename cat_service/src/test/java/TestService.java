import lab2.com.data.dao.OwnerDao;
import lab2.com.data.entities.Owner;
import lab2.com.data.models.Breed;
import lab2.com.dto.OwnerDto;
import lab2.com.service.KittyService;
import lab2.com.data.dao.KittyDao;
import lab2.com.data.models.Color;
import lab2.com.data.entities.Kitty;
import lab2.com.service.OwnerService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestService {
    @Test
    public void addKittyToOwnerTest() {
        KittyDao kittyDao = Mockito.mock(KittyDao.class);
        OwnerDao ownerDao = Mockito.mock(OwnerDao.class);
        KittyService kittyService = new KittyService(kittyDao);
        OwnerService ownerService = new OwnerService(ownerDao, kittyDao);

        Long ownerId = 3L;
        Long kittyId = 4L;

        Kitty kitty = new Kitty();
        kitty.setId(kittyId);
        kitty.setName("kiti");
        kitty.setColor(Color.CINNAMON);
        kitty.setBirthDate(LocalDate.now());
        kitty.setFriends(Collections.emptyList());
        kitty.setBreed(Breed.DVOROVAYA);

        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setBirthDate(LocalDate.now().minusDays(1));
        owner.setName("nastya");
        owner.setKitties(new ArrayList<>());

        Mockito.when(kittyDao.getKittyById(kittyId)).thenReturn(kitty);
        Mockito.when(ownerDao.getOwnerById(ownerId)).thenReturn(owner);

        ownerService.addKittyToOwner(kittyId, ownerId);

        assertEquals(ownerService.getOwnerById(ownerId).getId(),
                kittyService.getKittyById(kittyId).ownerId());
    }

    @Test
    public void ownerMappingTest() {
        KittyDao kittyDao = Mockito.mock(KittyDao.class);

        KittyService kittyService = new KittyService(kittyDao);

        Long ownerId = 1L;
        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setName("nastya");
        owner.setBirthDate(LocalDate.now());

        Kitty kitty1 = new Kitty();
        kitty1.setId(1L);
        kitty1.setName("persik");
        kitty1.setColor(Color.CHOCOLATE);
        kitty1.setFriends(Collections.emptyList());
        kitty1.setBirthDate(LocalDate.MIN);
        kitty1.setOwner(owner);
        kitty1.setBreed(Breed.BRITISH_SHORTHAIR);

        owner.setKitties(List.of(kitty1));
        Mockito.when(kittyDao.getOwner(1L)).thenReturn(owner);

        OwnerDto ownerDto = new OwnerDto(1L, "nastya", LocalDate.now(), List.of(1L));

        Assertions.assertEquals(kittyService.getOwnerOfKitty(kitty1.getId()), ownerDto);
    }


}
