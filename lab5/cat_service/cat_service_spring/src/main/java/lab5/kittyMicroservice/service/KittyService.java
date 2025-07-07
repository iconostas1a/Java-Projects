package lab5.kittyMicroservice.service;

import jakarta.persistence.EntityNotFoundException;
import lab5.kittyMicroservice.dto.KittyDto;
import lab5.kittyMicroservice.entity.Kitty;
import lab5.kittyMicroservice.repository.KittyRepository;
import lab5.kittyMicroservice.mapper.KittyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class KittyService {
    private final KittyRepository kittyRepository;

    @Autowired
    public KittyService(KittyRepository kittyRepository) {
        this.kittyRepository = kittyRepository;
    }


    public KittyDto save(KittyDto dto) {
        Kitty kitty = new Kitty();
        kitty.setName(dto.name());
        kitty.setBirthDate(dto.birthDate());
        kitty.setBreed(dto.breed());
        kitty.setOwnerId(dto.ownerId());
        kitty.setColor(dto.color());
        return KittyMapper.toDto(kittyRepository.save(kitty));
    }

    public KittyDto getKittyById(Long id) {
        Kitty kitty = kittyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Нет кота с таким id"));
        return KittyMapper.toDto(kitty);
    }

    public Long getOwnerIdByKittyId(Long kittyId) {
        return kittyRepository.findById(kittyId)
                .map(kitty -> kitty.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("Котик с id " + kittyId + " не найден"));
    }

    public void makeFriends(Long kittyId1, Long kittyId2) {
        if (kittyId1.equals(kittyId2)) {
            throw new IllegalArgumentException("Кот не может дружить сам с собой!");
        }

        Kitty kitty1 = kittyRepository.findById(kittyId1)
                .orElseThrow(() -> new RuntimeException("Котик с id " + kittyId1 + " не найден"));
        Kitty kitty2 = kittyRepository.findById(kittyId2)
                .orElseThrow(() -> new RuntimeException("Котик с id " + kittyId2 + " не найден"));

        kitty1.addFriend(kittyId2); 
        kittyRepository.save(kitty1);
        kittyRepository.save(kitty2);
    }

    public void unfriendKitties(Long kittyId1, Long kittyId2) {
        Kitty kitty1 = kittyRepository.findById(kittyId1)
                .orElseThrow(() -> new RuntimeException("Котик с id " + kittyId1 + " не найден"));
        Kitty kitty2 = kittyRepository.findById(kittyId2)
                .orElseThrow(() -> new RuntimeException("Котик с id " + kittyId2 + " не найден"));

        kitty1.removeFriend(kittyId2);
        kittyRepository.save(kitty1);
        kittyRepository.save(kitty2);
    }

    public void deleteById(Long id) {
        if (!kittyRepository.existsById(id)) {
            throw new RuntimeException("Нет кота с таким id " + id);
        }
        kittyRepository.deleteById(id);
    }

}