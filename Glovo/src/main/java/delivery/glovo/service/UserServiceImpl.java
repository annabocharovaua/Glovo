package delivery.glovo.service;

import delivery.glovo.dto.UserDto;
import delivery.glovo.mappers.UserMapper;
import delivery.glovo.model.Order;
import delivery.glovo.model.Role;
import delivery.glovo.model.User;
import delivery.glovo.repository.RoleRepository;
import delivery.glovo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("user");
        if (role == null) {
            role = Role.builder().name("user").build();
            roleRepository.save(role);
        }

        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return userMapper.toUserDtoList(users);
    }

    @Override
    public UserDto getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow();
        return userMapper.userToUserDto(user);
    }

    @Override
    public void updateUserById(Long id, UserDto userDto){
        User user = userRepository.findById(id).orElseThrow();
        if(Objects.equals(user.getEmail(), userDto.getEmail())) {
            user = userMapper.updateUser(user, userDto);
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

}