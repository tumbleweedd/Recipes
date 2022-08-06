package recipes.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipes.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String password;

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
