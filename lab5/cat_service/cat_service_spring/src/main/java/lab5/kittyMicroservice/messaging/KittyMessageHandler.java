package lab5.kittyMicroservice.messaging;

import lab5.kittyMicroservice.dto.KittyDto;
import lab5.kittyMicroservice.service.KittyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KittyMessageHandler {
    private final KittyService kittyService;

    public KittyMessageHandler(KittyService kittyService) {
        this.kittyService = kittyService;
    }

    @RabbitListener(queues = "kitty.getById")
    @SendTo("gateway.responses")
    public KittyDto getKittyById(Long id) {
        return kittyService.getKittyById(id);
    }

    @RabbitListener(queues = "kitty.makeFriends")
    public void handleMakeFriends(Map<String, Long> ids) {
        kittyService.makeFriends(ids.get("id1"), ids.get("id2"));
    }

    @RabbitListener(queues = "kitty.unfriendKitties")
    public void handleUnfriendKitties(Map<String, Long> ids) {
        kittyService.unfriendKitties(ids.get("id1"), ids.get("id2"));
    }

    @RabbitListener(queues = "kitty.delete")
    public void handleDeleteKitty(Map<String, Object> data) {
        Long kittyId = ((Number) data.get("id")).longValue();
        kittyService.deleteById(kittyId);
    }
}
