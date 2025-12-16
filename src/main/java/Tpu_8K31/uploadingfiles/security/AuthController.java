package Tpu_8K31.uploadingfiles.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> checkUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        return authService.checkPassword(username,password);
    }
}
