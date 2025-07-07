package lab2.com.data.entities;
import lab2.com.data.models.Breed;
import lab2.com.data.models.Color;
import jakarta.persistence.*;
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
    private Breed breed;
    @Column
    private Color color;
    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
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
