package com.uj.projects.booksplatform.user.mapper;

import com.uj.projects.booksplatform.user.dto.UserDto;
import com.uj.projects.booksplatform.user.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}
