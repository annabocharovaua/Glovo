package delivery.glovo.service;

import delivery.glovo.dto.UserDto;
import delivery.glovo.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    void deleteUserById(Long id);
    void updateUserById(Long id, UserDto userDto);
}
