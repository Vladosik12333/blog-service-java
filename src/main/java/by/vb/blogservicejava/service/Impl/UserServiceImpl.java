package by.vb.blogservicejava.service.Impl;

import by.vb.blogservicejava.dao.UserRepository;
import by.vb.blogservicejava.dto.User.AuthRequestDto;
import by.vb.blogservicejava.dto.User.AuthResponseDto;
import by.vb.blogservicejava.dto.User.UserCreateUpdateDto;
import by.vb.blogservicejava.dto.User.UserDto;
import by.vb.blogservicejava.entity.User;
import by.vb.blogservicejava.mapper.Impl.UserCreateUpdateMapper;
import by.vb.blogservicejava.mapper.Impl.UserMapper;
import by.vb.blogservicejava.service.JwtService;
import by.vb.blogservicejava.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final UserCreateUpdateMapper userCreateUpdateMapper;
	private final UserMapper userMapper;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	@Override
	public UserDto signUp(final UserCreateUpdateDto userCreateUpdateDto) {
		return Optional.of(userCreateUpdateDto)
				.map(userCreateUpdateMapper::mapTo)
				.map(userRepository::save)
				.map(userMapper::mapTo)
				.orElseThrow();
	}

	@Override
	public AuthResponseDto signIn(final AuthRequestDto authRequestDto)
			throws UsernameNotFoundException {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(),
						authRequestDto.getPassword()));

		String token = jwtService.generateToken(authRequestDto.getUsername());
		return AuthResponseDto.builder().accessToken(token).build();
	}

	@Override
	public UserDto getUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return Optional.of(user).map(userMapper::mapTo).orElseThrow();
	}
}
