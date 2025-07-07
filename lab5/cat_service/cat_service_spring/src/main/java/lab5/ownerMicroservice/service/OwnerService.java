package lab5.ownerMicroservice.service;

import lab5.ownerMicroservice.dto.OwnerDto;
import lab5.ownerMicroservice.entity.Owner;
import lab5.ownerMicroservice.mapper.OwnerMapper;
import lab5.ownerMicroservice.repository.OwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public OwnerDto getOwnerById(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Нет такого владельца"));
        return OwnerMapper.toDto(owner);
    }

    public OwnerDto save(Owner owner, Long userId) {
        owner.setUserId(userId);
        Owner savedOwner = ownerRepository.save(owner);
        return OwnerMapper.toDto(savedOwner);
    }

    public OwnerDto updateOwner(Long id, Owner updatedOwner) {
        Owner existing = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Нет такого владельца"));
        existing.setName(updatedOwner.getName());
        existing.setBirthDate(updatedOwner.getBirthDate());
        return OwnerMapper.toDto(ownerRepository.save(existing));
    }
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }
}
