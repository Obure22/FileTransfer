package Tpu_8K31.uploadingfiles.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        try {
            userService.userCreate(username,email,password);
            return ResponseEntity.ok().body("Регистрация успешна");
        }
        catch (DataIntegrityViolationException e){
            if (userService.userGetByName(username) != null) {
                return ResponseEntity.badRequest().body("Логин занят");
            }
            else {
                return ResponseEntity.badRequest().body("Почта занята");
            }
        }
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