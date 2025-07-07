package lab3.service;

import lab3.data.entity.Kitty;
import lab3.data.entity.Owner;
import lab3.dto.OwnerDto;
import lab3.mapper.OwnerMapper;
import lab3.repository.KittyRepository;
import lab3.repository.OwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final KittyRepository kittyRepository;

    public OwnerService(OwnerRepository ownerRepository, KittyRepository kittyRepository) {
        this.ownerRepository = ownerRepository;
        this.kittyRepository = kittyRepository;
    }

    public OwnerDto getOwnerById(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Нет такого владельца"));
        return OwnerMapper.toDto(owner);
    }

    public OwnerDto save(Owner owner) {
        Owner saved = ownerRepository.save(owner);
        return OwnerMapper.toDto(saved);
    }

    public OwnerDto updateOwner(Long id, Owner updatedOwner) {
        Owner existing = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Нет такого владельца"));

        existing.setName(updatedOwner.getName());
        existing.setBirthDate(updatedOwner.getBirthDate());
        return OwnerMapper.toDto(ownerRepository.save(existing));
    }
    public void addKittyToOwner(Long ownerId, Kitty kitty) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Нет такого владельца"));

        kitty.setOwner(owner);
        owner.getKitties().add(kitty);

        kittyRepository.save(kitty);
    }

    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }
}
