package lab3.controller;

import lab3.dto.KittyDto;
import lab3.dto.OwnerDto;
import lab3.models.Color;
import lab3.service.KittyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitties")
public class KittyController {
    private final KittyService kittyService;

    public KittyController(KittyService kittyService) {
        this.kittyService = kittyService;
    }

    @PostMapping
    public KittyDto createKitty(@RequestBody KittyDto kittyDto) {
        return kittyService.save(kittyDto);
    }

    @GetMapping("/{id}")
    public KittyDto getKitty(@PathVariable Long id) {
        return kittyService.getKittyById(id);
    }

    @GetMapping("/{id}/owner")
    public OwnerDto getKittyOwner(@PathVariable Long id) {
        return kittyService.getKittyOwner(id);
    }

    @PostMapping("/{id1}/friends/{id2}")
    public void makeFriends(@PathVariable Long id1, @PathVariable Long id2) {
        kittyService.makeFriends(id1, id2);
    }

    @DeleteMapping("/{id1}/friends/{id2}")
    public void unfriendKitties(@PathVariable Long id1, @PathVariable Long id2) {
        kittyService.unfriendKitties(id1, id2);
    }

    @GetMapping
    public Page<KittyDto> getKitties(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) Color color,
            Pageable pageable
    ) {
        return kittyService.findKitties(name, breed, color, pageable);
    }

    @GetMapping("/top5")
    public List<KittyDto> getTop5ByColor(@RequestParam Color color) {
        return kittyService.getTop5ByColor(color);
    }

    @DeleteMapping("/{id}")
    public void deleteKitty(@PathVariable Long id) {
        kittyService.deleteById(id);
    }
}
