package lab3.data.entity;

import lab3.models.Breed;
import lab3.models.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private Breed breed;
    @Column
    private Color color;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    private List<Kitty> friends = new ArrayList<>();

    public void addFriend(Kitty friend) {
        if (friend == this) throw new IllegalArgumentException("Кот не может дружить сам с собой!");
        this.friends.add(friend);
        friend.getFriends().add(this);
    }
    public void removeFriend(Kitty friend) {
        this.friends.remove(friend);
        friend.getFriends().remove(this);
    }

}
