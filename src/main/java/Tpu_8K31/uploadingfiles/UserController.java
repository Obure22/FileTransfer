package Tpu_8K31.uploadingfiles;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Создать нового пользователя
    @PostMapping("/register")
    public UserEntity createUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        String encodedPassword = passwordEncoder.encode(password);
        UserEntity user = new UserEntity(username, email, encodedPassword);
        return userRepository.save(user);
    }

    // Получить всех пользователей
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Получить пользователя по имени
    @GetMapping("/{username}")
    public UserEntity getUserByName(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }
}