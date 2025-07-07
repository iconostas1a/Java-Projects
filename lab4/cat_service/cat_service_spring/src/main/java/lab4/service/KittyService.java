package lab4.service;

import jakarta.persistence.EntityNotFoundException;
import lab4.KittySpecifications;
import lab4.data.entity.Kitty;
import lab4.data.entity.Owner;
import lab4.data.entity.User;
import lab4.dto.KittyDto;
import lab4.dto.OwnerDto;
import lab4.mapper.KittyMapper;
import lab4.mapper.OwnerMapper;
import lab4.models.Color;
import lab4.repository.KittyRepository;
import lab4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KittyService {
    private final KittyRepository kittyRepository;
    private final UserRepository userRepository;

    @Autowired
    public KittyService(KittyRepository kittyRepository, UserRepository userRepository) {
        this.kittyRepository = kittyRepository;
        this.userRepository = userRepository;
    }

    public KittyDto save(KittyDto dto) {
        Kitty kitty = new Kitty();
        kitty.setName(dto.name());
        kitty.setBirthDate(dto.birthDate());
        kitty.setBreed(dto.breed());
        kitty.setColor(dto.color());
        return KittyMapper.toDto(kittyRepository.save(kitty));
    }

    public KittyDto getKittyById(Long id) {
        Kitty kitty = kittyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Нет кота с таким id"));
        return KittyMapper.toDto(kitty);
    }

    public OwnerDto getKittyOwner(Long kittyId) {
        Kitty kitty = kittyRepository.findById(kittyId)
                .orElseThrow(() -> new RuntimeException("Кот с id " + kittyId + " не найден"));

        Owner owner = kitty.getOwner();
        if (owner == null) {
            throw new RuntimeException("У кота нет владельца");
        }

        return OwnerMapper.toDto(owner);
    }

    public void makeFriends(Long kittyId1, Long kittyId2) {
        Kitty kitty1 = kittyRepository.findById(kittyId1)
                .orElseThrow(() -> new RuntimeException("Котик с id " + kittyId1 + " не найден"));
        Kitty kitty2 = kittyRepository.findById(kittyId2)
                .orElseThrow(() -> new RuntimeException("Котик с id " + kittyId2 + " не найден"));

        kitty1.addFriend(kitty2);
        kittyRepository.save(kitty1);
        kittyRepository.save(kitty2);
    }

    public void unfriendKitties(Long kittyId1, Long kittyId2) {
        Kitty kitty1 = kittyRepository.findById(kittyId1)
                .orElseThrow(() -> new RuntimeException("Котик с id " + kittyId1 + " не найден"));
        Kitty kitty2 = kittyRepository.findById(kittyId2)
                .orElseThrow(() -> new RuntimeException("Котик с id " + kittyId2 + " не найден"));

        kitty1.removeFriend(kitty2);
        kittyRepository.save(kitty1);
        kittyRepository.save(kitty2);
    }
    public Page<KittyDto> findKitties(String name, String breed, Color color, Pageable pageable) {
        Specification<Kitty> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(KittySpecifications.hasName(name));
        }
        if (breed != null) {
            spec = spec.and(KittySpecifications.hasBreed(breed));
        }
        if (color != null) {
            spec = spec.and(KittySpecifications.hasColor(color));
        }
        return kittyRepository.findAll(spec, pageable)
                .map(KittyMapper::toDto);
    }
    public List<KittyDto> getTop5ByColor(Color color) {
        return kittyRepository.findTop5ByColor(color)
                .stream()
                .map(KittyMapper::toDto)
                .toList();
    }

    public void deleteById(Long id) {
        if (!kittyRepository.existsById(id)) {
            throw new RuntimeException("Нет кота с таким id " + id);
        }
        kittyRepository.deleteById(id);
    }
    public boolean isKittyOwnedByUser(Long kittyId, String username) {
        Kitty kitty = kittyRepository.findById(kittyId)
                .orElseThrow(() -> new EntityNotFoundException("Кот не найден"));

        Owner owner = kitty.getOwner();
        if (owner == null) {
            return false;
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        return owner.getId().equals(user.getOwner().getId());
    }

}
