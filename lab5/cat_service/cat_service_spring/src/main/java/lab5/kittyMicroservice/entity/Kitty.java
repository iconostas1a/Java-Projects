package lab5.kittyMicroservice.entity;

import jakarta.persistence.*;
import lab5.kittyMicroservice.models.Breed;
import lab5.kittyMicroservice.models.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "kitties")
public class Kitty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDate birthDate;
    @Column
    @Enumerated(EnumType.STRING)
    private Breed breed;
    @Column
    @Enumerated(EnumType.STRING)
    private Color color;

    private Long ownerId;
    @ElementCollection
    @CollectionTable(name = "kitty_friends", joinColumns = @JoinColumn(name = "kitty_id"))
    @Column(name = "friend_id")
    private List<Long> friendsId = new ArrayList<>();

    public void addFriend(Long friendId) {
        if (friendId.equals(this.id)) throw new IllegalArgumentException("Кот не может дружить сам с собой!");
        this.friendsId.add(friendId);
    }

    public void removeFriend(Long friendId) {
        this.friendsId.remove(friendId);
    }

}
