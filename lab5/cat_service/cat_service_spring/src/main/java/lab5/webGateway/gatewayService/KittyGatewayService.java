package lab5.webGateway.gatewayService;

import lab5.webGateway.Color;
import lab5.webGateway.dto.OwnerDto;
import lab5.webGateway.dto.KittyDto;
import lab5.webGateway.Role;
import lab5.webGateway.entity.User;
import lab5.webGateway.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KittyGatewayService {
    private final RabbitTemplate rabbitTemplate;
    private final UserRepository userRepository;

    public KittyGatewayService(RabbitTemplate rabbitTemplate, UserRepository userRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.userRepository = userRepository;
    }

    public KittyDto save(KittyDto kittyDto) {
        return (KittyDto) rabbitTemplate.convertSendAndReceive(
                "kitty.exchange", "kitty.routingKey.save", kittyDto);
    }

    public KittyDto getKittyById(Long id) {
        return (KittyDto) rabbitTemplate.convertSendAndReceive(
                "kitty.exchange", "kitty.routingKey.get", id);
    }

    public OwnerDto getKittyOwner(Long kittyId) {
        return (OwnerDto) rabbitTemplate.convertSendAndReceive(
                "kitty.exchange", "kitty.routingKey.getOwner", kittyId);
    }

    public void makeFriends(Long id1, Long id2) {
        rabbitTemplate.convertSendAndReceive(
                "kitty.exchange", "kitty.routingKey.friends",
                Map.of("id1", id1, "id2", id2));
    }

    public void unfriendKitties(Long id1, Long id2) {
        rabbitTemplate.convertSendAndReceive(
                "kitty.exchange", "kitty.routingKey.unfriends",
                Map.of("id1", id1, "id2", id2));
    }

    public Page<KittyDto> findKitties(String name, String breed, Color color, Pageable pageable) {
        Map<String, Object> request = Map.of(
                "name", name,
                "breed", breed,
                "color", color,
                "page", pageable.getPageNumber(),
                "size", pageable.getPageSize()
        );

        Map<String, Object> response = (Map<String, Object>) rabbitTemplate.convertSendAndReceive(
                "kitty.exchange", "kitty.routingKey.find", request);

        List<KittyDto> content = (List<KittyDto>) response.get("content");
        int total = (int) response.get("total");

        return new PageImpl<>(content, pageable, total);
    }

    public void deleteKitty(Long kittyId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Long ownerId = (Long) rabbitTemplate.convertSendAndReceive(
                "kitty.exchange", "kitty.routingKey.getOwnerId", kittyId
        );

        if (!ownerId.equals(user.getId()) && user.getRole() != Role.ROLE_ADMIN) {
            throw new AccessDeniedException("Вы не можете удалить чужого кота");
        }

        Map<String, Object> data = Map.of("id", kittyId, "username", username);
        rabbitTemplate.convertSendAndReceive("kitty.exchange", "kitty.routingKey.delete", data);
    }

    public boolean isKittyOwnedByUser(Long kittyId, String username) {
        Map<String, Object> request = Map.of("id", kittyId, "username", username);
        return (Boolean) rabbitTemplate.convertSendAndReceive(
                "kitty.exchange", "kitty.routingKey.checkOwnership", request);
    }
}
