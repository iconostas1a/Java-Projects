package lab5.webGateway.gatewayService;

import lab5.webGateway.dto.OwnerDto;
import lab5.webGateway.dto.KittyDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class OwnerGatewayService {
    private final RabbitTemplate rabbitTemplate;
    private static final String OWNER_EXCHANGE = "owner.exchange";

    public OwnerGatewayService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public OwnerDto save(OwnerDto ownerDto) {
        return (OwnerDto) rabbitTemplate.convertSendAndReceive(
                OWNER_EXCHANGE,
                "owner.save",
                ownerDto
        );
    }

    public OwnerDto getOwnerById(Long id) {
        return (OwnerDto) rabbitTemplate.convertSendAndReceive(
                OWNER_EXCHANGE,
                "owner.get",
                id
        );
    }

    public OwnerDto updateOwner(Long id, OwnerDto updatedDto) {
        OwnerUpdateRequest updateRequest = new OwnerUpdateRequest(id, updatedDto);
        return (OwnerDto) rabbitTemplate.convertSendAndReceive(
                OWNER_EXCHANGE,
                "owner.update",
                updateRequest
        );
    }

    public void deleteOwner(Long id) {
        rabbitTemplate.convertAndSend(
                OWNER_EXCHANGE,
                "owner.delete",
                id
        );
    }

    public void addKittyToOwner(Long ownerId, KittyDto kittyDto) {
        AddKittyRequest request = new AddKittyRequest(ownerId, kittyDto);
        rabbitTemplate.convertAndSend(
                OWNER_EXCHANGE,
                "owner.addKitty",
                request
        );
    }

    public record OwnerUpdateRequest(Long id, OwnerDto updatedDto) {}
    public record AddKittyRequest(Long ownerId, KittyDto kitty) {}
}
