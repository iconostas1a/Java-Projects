package lab2.com.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OwnerDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private List<Long> kittiesId;

    public OwnerDto(Long id, String name, LocalDate birthDate, List<Long> kittiesId) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.kittiesId = kittiesId;
    }

    public OwnerDto() {
    }
}