package Tpu_8K31.uploadingfiles.security;

import org.springframework.http.ResponseCookie;

public interface AuthService {
    ResponseCookie checkPassword(String username, String password);
}
