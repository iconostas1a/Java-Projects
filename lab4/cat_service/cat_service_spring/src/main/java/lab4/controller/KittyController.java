package lab4.controller;

import lab4.dto.KittyDto;
import lab4.dto.OwnerDto;
import lab4.models.Color;
import lab4.service.KittyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@EnableMethodSecurity
@RequestMapping("/kitties")
public class KittyController {
    private final KittyService kittyService;

    public KittyController(KittyService kittyService) {
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

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/top5")
    public List<KittyDto> getTop5ByColor(@RequestParam Color color) {
        return kittyService.getTop5ByColor(color);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteKitty(@PathVariable Long id, Principal principal) {
        String username = principal.getName();
        if (!kittyService.isKittyOwnedByUser(id, username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        kittyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
