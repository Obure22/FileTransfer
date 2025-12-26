package Tpu_8K31.uploadingfiles.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
//@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Создать нового пользователя
    @PostMapping("/register")
    public UserEntity createUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        return userService.userCreate(username,email,password);
    }

    // Получить всех пользователей
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.userAllGet();
    }

    // Получить пользователя по имени
    @GetMapping("/{username}")
    public UserEntity getUserByName(@PathVariable String username) {
        return userService.userGetByName(username);
    }
}