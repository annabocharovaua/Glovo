package delivery.glovo.mappers;

import delivery.glovo.dto.UserDto;
import delivery.glovo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface UserMapper {
    @Mappings({
            @Mapping(target = "name", expression = "java(userDto.getFirstName() + \" \" + userDto.getLastName())"),
            @Mapping(target = "roles", ignore = true)
    })
    User userDtoToUser (UserDto userDto);

    @Mappings({
            @Mapping(target = "firstName", expression = "java(user.getName().split(\" \")[0])"),
            @Mapping(target = "lastName", expression = "java(user.getName().split(\" \")[1])"),
    })
    UserDto userToUserDto(User user);

    List<UserDto> toUserDtoList(Iterable<User> users);

    @Mappings({
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "name", expression = "java(userDto.getFirstName() + \" \" + userDto.getLastName())"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "password", source = "userDto.password"),
            @Mapping(target = "roles", source = "user.roles")
    })
    User updateUser(User user, UserDto userDto);
}