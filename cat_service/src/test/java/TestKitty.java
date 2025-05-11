import lab2.com.data.dao.KittyDao;
import lab2.com.data.entities.Kitty;
import lab2.com.service.KittyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestKitty {

    @Test
    public void testMakeFriends() {
        KittyDao kittyDao = Mockito.mock(KittyDao.class);
        KittyService kittyService = new KittyService(kittyDao);

        Kitty kitty1 = new Kitty();
        kitty1.setId(1L);
        kitty1.setName("kis");
        kitty1.setFriends(new ArrayList<>());

        Kitty kitty2 = new Kitty();
        kitty2.setId(2L);
        kitty2.setName("mur");
        kitty2.setFriends(new ArrayList<>());

        when(kittyDao.getKittyById(1L)).thenReturn(kitty1);
        when(kittyDao.getKittyById(2L)).thenReturn(kitty2);

        kittyService.makeFriends(1L, 2L);
        assertTrue(kitty1.getFriends().contains(kitty2));
        assertTrue(kitty2.getFriends().contains(kitty1));
    }


    @Test
    public void testUnfriendKitties() {
        KittyDao kittyDao = Mockito.mock(KittyDao.class);
        KittyService kittyService = new KittyService(kittyDao);

        Kitty kitty1 = new Kitty();
        kitty1.setId(1L);
        kitty1.setName("mur");
        kitty1.setFriends(new ArrayList<>());

        Kitty kitty2 = new Kitty();
        kitty2.setId(2L);
        kitty2.setName("kis");
        kitty2.setFriends(new ArrayList<>());

        kitty1.getFriends().add(kitty2);
        kitty2.getFriends().add(kitty1);

        when(kittyDao.getKittyById(1L)).thenReturn(kitty1);
        when(kittyDao.getKittyById(2L)).thenReturn(kitty2);

        kittyService.unfriendKitties(1L, 2L);

        assertFalse(kitty1.getFriends().contains(kitty2));
        assertFalse(kitty2.getFriends().contains(kitty1));

        verify(kittyDao).updateKitty(kitty1);
    }
}


