package lab5.ownerMicroservice.messaging;

import lab5.ownerMicroservice.dto.OwnerDto;
import lab5.ownerMicroservice.entity.Owner;
import lab5.ownerMicroservice.service.OwnerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OwnerMessageHandler {

    private final OwnerService ownerService;

    public OwnerMessageHandler(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RabbitListener(queues = "owner.save")
    @SendTo("gateway.responses")
    public OwnerDto handleSaveOwner(Owner owner, Long id) {
        return ownerService.save(owner, id);
    }

    @RabbitListener(queues = "owner.getById")
    @SendTo("gateway.responses")
    public OwnerDto handleGetOwner(Long id) {
        return ownerService.getOwnerById(id);
    }

    @RabbitListener(queues = "owner.update")
    @SendTo("gateway.responses")
    public OwnerDto handleUpdateOwner(Long id, Owner owner) {
        return ownerService.updateOwner(id, owner);
    }

    @RabbitListener(queues = "owner.delete")
    public void handleDeleteOwner(Long id) {
        ownerService.deleteOwner(id);
    }

}
