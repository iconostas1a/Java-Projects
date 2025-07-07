package lab4.dto;

import lab4.data.entity.User;

public class UserDto {
    public Long id;
    public String username;
    public String role;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole().name();
    }
}
