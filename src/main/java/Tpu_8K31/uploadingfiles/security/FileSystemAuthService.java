package Tpu_8K31.uploadingfiles.security;

import Tpu_8K31.uploadingfiles.UserEntity;
import Tpu_8K31.uploadingfiles.UserRepository;
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
    public ResponseEntity<?> checkPassword(String username, String password) {

        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Пользователь не найден");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity
                    .badRequest()
                    .body("Неверный пароль");
        }
        String jwt = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
