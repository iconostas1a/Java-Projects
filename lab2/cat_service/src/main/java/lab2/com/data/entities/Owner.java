package lab2.com.data.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDate birthDate;
    @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.LAZY) // TODO проверить
    private List<Kitty> kitties =  new ArrayList<>();

    public Owner(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.kitties = new ArrayList<>();
    }
    public void addKitty(Kitty kitty) {
        if (kitty == null) {
            // TODO кинуть ошибку
        }

        if (!kitties.contains(kitty)) {
            kitties.add(kitty);
            kitty.setOwner(this);
        }
    }

    public void removeKitty(Kitty kitty) {
        if (kitty == null) {
            // TODO кинуть ошибку
        };
        if (kitties.remove(kitty)) {
            kitty.setOwner(null);
        }
    }
}
