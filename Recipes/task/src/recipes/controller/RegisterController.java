package recipes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recipes.DTO.UserDTO;
import recipes.model.User;
import recipes.service.UserServie;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RegisterController {
    private final UserServie userServie;

    public RegisterController(UserServie userServie) {
        this.userServie = userServie;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPoint(@Valid @RequestBody User user) {

        return userServie.registerUser(user);
    }
}
