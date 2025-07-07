package lab3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private List<Long> kittiesId;
}
