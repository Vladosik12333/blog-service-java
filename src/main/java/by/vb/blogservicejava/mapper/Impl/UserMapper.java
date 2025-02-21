package by.vb.blogservicejava.mapper.Impl;

import by.vb.blogservicejava.dto.UserDto;
import by.vb.blogservicejava.entity.User;
import by.vb.blogservicejava.mapper.Mapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper implements Mapper<User, UserDto> {

	@Override
	public UserDto mapTo(@NotNull final User fromObject) {
		Objects.requireNonNull(fromObject, "User cannot be null");

		UserDto userDto = new UserDto();

		userDto.setId(fromObject.getId());
		userDto.setUsername(fromObject.getUsername());
		userDto.setFirstName(fromObject.getFirstName());
		userDto.setLastName(fromObject.getLastName());

		return userDto;
	}
}
