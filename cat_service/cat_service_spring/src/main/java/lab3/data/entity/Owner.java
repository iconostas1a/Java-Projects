package lab3.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Kitty> kitties =  new ArrayList<>();

    public Owner(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.kitties = new ArrayList<>();
    }
    public void addKitty(Kitty kitty) {
        if (kitty == null) {
            throw new IllegalStateException("Нет кота");
        }

        if (!kitties.contains(kitty)) {
            kitties.add(kitty);
            kitty.setOwner(this);
        }
    }

    public void removeKitty(Kitty kitty) {
        if (kitty == null) {
            throw new IllegalStateException("Нет кота");
        };
        if (kitties.remove(kitty)) {
            kitty.setOwner(null);
        }
    }
}