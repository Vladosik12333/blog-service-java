package by.vb.blogservicejava.controller;

import by.vb.blogservicejava.dto.AuthRequestDto;
import by.vb.blogservicejava.dto.AuthResponseDto;
import by.vb.blogservicejava.dto.UserCreateUpdateDto;
import by.vb.blogservicejava.dto.UserDto;
import by.vb.blogservicejava.dto.response.SuccessResponseDto;
import by.vb.blogservicejava.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users", description = "The group of end-points to work with User entity.")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<SuccessResponseDto<UserDto>> signUp(
			@Valid @RequestBody
			UserCreateUpdateDto userCreateUpdateDto
	) {
		UserDto user = userService.signUp(userCreateUpdateDto);

		SuccessResponseDto<UserDto> response = new SuccessResponseDto<>();

		response.setData(user);
		response.setCode(HttpStatus.OK.value());

		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/signin")
	public ResponseEntity<SuccessResponseDto<AuthResponseDto>> signIn(
			@Valid @RequestBody
			AuthRequestDto authRequestDto
	) {
		AuthResponseDto authDto = userService.signIn(authRequestDto);

		SuccessResponseDto<AuthResponseDto> response = new SuccessResponseDto<>();

		response.setData(authDto);
		response.setCode(HttpStatus.OK.value());

		return ResponseEntity.ok().body(response);
	}
}
