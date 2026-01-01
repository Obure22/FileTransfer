package Tpu_8K31.uploadingfiles.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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
        ResponseCookie authСookie = authService.checkPassword(username,password);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE,authСookie.toString())
                .body("Вход выполнен");
    }
}
