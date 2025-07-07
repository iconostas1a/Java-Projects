package lab2.com.data.dao;

import lab2.com.data.entities.Kitty;
import lab2.com.data.entities.Owner;

import java.util.List;

public interface KittyDao {
    void addKitty(Kitty kitty);
    void updateKitty(Kitty kitty);
    Owner getOwner(Long kittyId);

    void deleteAllKitties();

    Kitty getKittyById(Long id);
    List<Kitty> getAllKitties();
    void deleteKittyById(Long id);
}
