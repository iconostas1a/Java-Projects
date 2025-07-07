package lab5.webGateway.controller;

import lab5.webGateway.dto.KittyDto;
import lab5.webGateway.dto.OwnerDto;
import lab5.webGateway.gatewayService.OwnerGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerGatewayService ownerService;

    @Autowired
    public OwnerController(OwnerGatewayService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public OwnerDto createOwner(@RequestBody OwnerDto ownerDto) {
        return ownerService.save(ownerDto);
    }

    @GetMapping("/{id}")
    public OwnerDto getOwner(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }

    @PutMapping("/{id}")
    public OwnerDto updateOwner(@PathVariable Long id, @RequestBody OwnerDto ownerDto) {
        return ownerService.updateOwner(id, ownerDto);
    }

    @PostMapping("/{ownerId}/kitties")
    public ResponseEntity<Void> addKittyToOwner(
            @PathVariable Long ownerId,
            @RequestBody KittyDto kittyDto) {
        ownerService.addKittyToOwner(ownerId, kittyDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
}