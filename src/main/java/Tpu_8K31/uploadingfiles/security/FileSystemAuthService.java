package Tpu_8K31.uploadingfiles.security;

import Tpu_8K31.uploadingfiles.user.UserEntity;
import Tpu_8K31.uploadingfiles.user.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FileSystemAuthService implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public FileSystemAuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtUtil jwtUtil){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseCookie checkPassword(String username, String password) {
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new LoginException("Пользователь не найден");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new LoginException("Неверный пароль");
        }
        String jwt = jwtUtil.generateToken(user.getUsername());

        ResponseCookie jwtCookie = ResponseCookie.from("token", jwt)
                .httpOnly(true)       // JS не сможет прочитать эту куку (защита от XSS)
                .secure(false)        // true для HTTPS, false для localhost (http) ПОМЕНЯТЬ ПРИ ДЕПЛОЕ!
                .path("/")            // Кука доступна для всего домена
                .maxAge(60 * 60)      // Время жизни (1 час, как и у токена)
                .sameSite("Strict")   // Защита от CSRF (или "Lax")
                .build();

        return jwtCookie;
    }
}
