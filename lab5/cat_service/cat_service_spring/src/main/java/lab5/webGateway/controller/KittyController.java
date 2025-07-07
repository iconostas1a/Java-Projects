package lab5.webGateway.controller;

import lab5.webGateway.dto.KittyDto;
import lab5.webGateway.dto.OwnerDto;
import lab5.webGateway.Color;
import lab5.webGateway.gatewayService.KittyGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/kitties")
public class KittyController {

    private final KittyGatewayService kittyService;

    @Autowired
    public KittyController(KittyGatewayService kittyService) {
        this.kittyService = kittyService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public KittyDto createKitty(@RequestBody KittyDto kittyDto) {
        return kittyService.save(kittyDto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public KittyDto getKitty(@PathVariable Long id) {
        return kittyService.getKittyById(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}/owner")
    public OwnerDto getKittyOwner(@PathVariable Long id) {
        return kittyService.getKittyOwner(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/{id1}/friends/{id2}")
    public void makeFriends(@PathVariable Long id1, @PathVariable Long id2) {
        kittyService.makeFriends(id1, id2);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/{id1}/friends/{id2}")
    public void unfriendKitties(@PathVariable Long id1, @PathVariable Long id2) {
        kittyService.unfriendKitties(id1, id2);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public Page<KittyDto> getKitties(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) Color color,
            Pageable pageable
    ) {
        return kittyService.findKitties(name, breed, color, pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKitty(@PathVariable Long id, Principal principal) {
        kittyService.deleteKitty(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}
