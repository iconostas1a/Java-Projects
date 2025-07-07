package lab2.com.data.dao;

import lab2.com.data.entities.Owner;

public interface OwnerDao {
    void addOwner(Owner owner);
    void updateOwner(Owner owner);
    void addKittyToOwner(Long kittyId1, Long ownerId);
    void deleteOwnerById(Long id);
    Owner getOwnerById(Long id);
}
