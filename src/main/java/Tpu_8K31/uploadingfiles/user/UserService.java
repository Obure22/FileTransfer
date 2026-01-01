package Tpu_8K31.uploadingfiles.user;

import java.util.List;

public interface UserService {
    void userCreate(String username, String email, String password);
    List<UserEntity> userAllGet();
    UserEntity userGetByName(String username);

}
