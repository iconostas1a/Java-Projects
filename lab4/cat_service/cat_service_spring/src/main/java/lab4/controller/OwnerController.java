package lab4.controller;
import lab4.data.entity.Kitty;
import lab4.dto.OwnerDto;
import lab4.data.entity.Owner;
import lab4.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public OwnerDto createOwner(@RequestBody Owner owner) {
        return ownerService.save(owner);
    }

    @GetMapping("/{id}")
    public OwnerDto getOwner(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }

    @PutMapping("/{id}")
    public OwnerDto updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
        return ownerService.updateOwner(id, owner);
    }

    @PostMapping("/{ownerId}/kitties")
    public ResponseEntity<Void> addKittyToOwner(
            @PathVariable Long ownerId,
            @RequestBody Kitty kitty) {
        ownerService.addKittyToOwner(ownerId, kitty);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }
}
