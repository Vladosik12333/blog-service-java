package by.vb.blogservicejava.service;

import by.vb.blogservicejava.dto.AuthRequestDto;
import by.vb.blogservicejava.dto.AuthResponseDto;
import by.vb.blogservicejava.dto.UserCreateUpdateDto;
import by.vb.blogservicejava.dto.UserDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
	UserDto signUp(final UserCreateUpdateDto userCreateUpdateDto);
	AuthResponseDto signIn(final AuthRequestDto authRequestDto) throws UsernameNotFoundException;
}
