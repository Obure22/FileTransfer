package Tpu_8K31.uploadingfiles.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserSystemService implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserSystemService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void userCreate(String username, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        UserEntity user = new UserEntity(username, email, encodedPassword);
        try{
            userRepository.save(user);
        }
        catch (DataIntegrityViolationException e){
            if (userGetByName(username) != null){
                throw new UserCreationException("Логин занят");
            }
            else{
                throw new UserCreationException("Почта занята");
            }
        }
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
