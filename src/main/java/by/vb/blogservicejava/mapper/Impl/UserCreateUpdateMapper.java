package by.vb.blogservicejava.mapper.Impl;

import by.vb.blogservicejava.dto.User.UserCreateUpdateDto;
import by.vb.blogservicejava.entity.RoleType;
import by.vb.blogservicejava.entity.User;
import by.vb.blogservicejava.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateUpdateMapper implements Mapper<UserCreateUpdateDto, User> {
	private final PasswordEncoder encoder;

	@Override
	public User mapTo(final UserCreateUpdateDto fromObject) {
		User user = new User();

		user.setUsername(fromObject.getUsername());
		user.setPassword(encoder.encode(fromObject.getPassword()));
		user.setFirstName(fromObject.getFirstName());
		user.setLastName(fromObject.getLastName());
		user.setRole(RoleType.USER);

		return user;
	}
}
