package recipes.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.enums.Role;
import recipes.model.User;
import recipes.repository.UserRepository;
import recipes.service.UserServie;

@Service
public class UserServiceImpl implements UserServie {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?> registerUser(User user) {
        if (userRepository.findUserEntityByEmailIgnoreCase(user.getEmail()).isEmpty() &&
                user.getPassword().trim().matches(".{8,}")) {
            User newUSer = User.builder()
                    .password(passwordEncoder.encode(user.getPassword()))
                    .email(user.getEmail())
                    .role(Role.USER)
                    .build();
            userRepository.save(newUSer);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
