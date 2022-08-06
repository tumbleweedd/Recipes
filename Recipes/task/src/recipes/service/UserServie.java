package recipes.service;

import org.springframework.http.ResponseEntity;
import recipes.DTO.UserDTO;
import recipes.model.User;

public interface UserServie {
    ResponseEntity<?> registerUser(User user);
}
