package by.vb.blogservicejava.service.Impl;

import by.vb.blogservicejava.dao.UserRepository;
import by.vb.blogservicejava.dto.*;
import by.vb.blogservicejava.mapper.Impl.UserCreateUpdateMapper;
import by.vb.blogservicejava.mapper.Impl.UserMapper;
import by.vb.blogservicejava.service.JwtService;
import by.vb.blogservicejava.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(),
						authRequestDto.getPassword()));

		if (authentication.isAuthenticated()) {
			return AuthResponseDto.builder().accessToken(jwtService.generateToken(
					authRequestDto.getUsername())).build();
		} else {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	}
}
