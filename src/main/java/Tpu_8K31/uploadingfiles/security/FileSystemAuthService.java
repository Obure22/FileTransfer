package Tpu_8K31.uploadingfiles.security;

import Tpu_8K31.uploadingfiles.UserEntity;
import Tpu_8K31.uploadingfiles.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class FileSystemAuthService implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public FileSystemAuthService(PasswordEncoder passwordEncoder, UserRepository userRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> checkPassword(String username, String password) {

        // String result = passwordEncoder.encode(password); для регистрации
        UserEntity user = userRepository.findByUsername(username);

        if (passwordEncoder.matches(password, user.getPassword() ) ) {

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    Collections.emptyList()
            );

            return ResponseEntity.ok("Вход одобрен");

        }
        else {
            return ResponseEntity.badRequest().body("Неверные данные");
        }
    }
}
