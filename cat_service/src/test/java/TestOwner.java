import lab2.com.data.dao.KittyDao;
import lab2.com.data.dao.OwnerDao;
import lab2.com.data.entities.Owner;
import lab2.com.dto.OwnerDto;
import lab2.com.service.OwnerService;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
public class TestOwner {
    @Test
    public void updateOwnerTest() {
        OwnerDao ownerDao = Mockito.mock(OwnerDao.class);
        KittyDao kittyDao = Mockito.mock(KittyDao.class);
        OwnerService ownerService = new OwnerService(ownerDao, kittyDao);

        Long ownerId = 1L;
        String newName = "mynameis";
        LocalDate newBirthDate = LocalDate.of(2001, 9, 11);
        Owner updatedOwner = new Owner();
        updatedOwner.setId(ownerId);
        updatedOwner.setName(newName);
        updatedOwner.setBirthDate(newBirthDate);
        Mockito.when(ownerDao.getOwnerById(ownerId)).thenReturn(updatedOwner);

        ownerService.updateOwner(ownerId, newName, newBirthDate);
        OwnerDto result = ownerService.getOwnerById(ownerId);
        assertEquals(newName, result.getName());
        assertEquals(newBirthDate, result.getBirthDate());
    }
}