package lab4.service;

import lab4.data.entity.Kitty;
import lab4.data.entity.Owner;
import lab4.data.entity.User;
import lab4.dto.OwnerDto;
import lab4.mapper.OwnerMapper;
import lab4.repository.KittyRepository;
import lab4.repository.OwnerRepository;
import lab4.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final KittyRepository kittyRepository;
    private final UserRepository userRepository;

    public OwnerService(OwnerRepository ownerRepository, KittyRepository kittyRepository, UserRepository userRepository) {
        this.ownerRepository = ownerRepository;
        this.kittyRepository = kittyRepository;
        this.userRepository = userRepository;
    }

    public OwnerDto getOwnerById(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Нет такого владельца"));
        return OwnerMapper.toDto(owner);
    }

    public OwnerDto save(Owner owner) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        owner.setUser(user);
        Owner savedOwner = ownerRepository.save(owner);
        user.setOwner(savedOwner);
        userRepository.save(user);
        return OwnerMapper.toDto(savedOwner);
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
