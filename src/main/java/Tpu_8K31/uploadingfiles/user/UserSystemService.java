package Tpu_8K31.uploadingfiles.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSystemService implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserSystemService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity userCreate(String username, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        UserEntity user = new UserEntity(username, email, encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> userAllGet() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity userGetByName(String username) {
        return userRepository.findByUsername(username);
    }
}
