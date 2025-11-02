package Tpu_8K31.uploadingfiles;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Создать нового пользователя
    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
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