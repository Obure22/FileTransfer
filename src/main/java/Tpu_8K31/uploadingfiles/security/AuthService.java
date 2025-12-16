package Tpu_8K31.uploadingfiles.security;

import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> checkPassword(String username, String password);
}
