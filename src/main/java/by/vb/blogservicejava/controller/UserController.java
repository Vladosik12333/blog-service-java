package by.vb.blogservicejava.controller;

import by.vb.blogservicejava.dto.User.AuthRequestDto;
import by.vb.blogservicejava.dto.User.AuthResponseDto;
import by.vb.blogservicejava.dto.User.UserCreateUpdateDto;
import by.vb.blogservicejava.dto.User.UserDto;
import by.vb.blogservicejava.dto.Response.SuccessResponseDto;
import by.vb.blogservicejava.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	) throws BadRequestException {
		AuthResponseDto authDto = userService.signIn(authRequestDto);

		SuccessResponseDto<AuthResponseDto> response = new SuccessResponseDto<>();

		response.setData(authDto);
		response.setCode(HttpStatus.OK.value());

		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/current")
	public ResponseEntity<SuccessResponseDto<UserDto>> getCurrentUser() {
		UserDto user = userService.getUser();

		SuccessResponseDto<UserDto> response = new SuccessResponseDto<>();

		response.setData(user);
		response.setCode(HttpStatus.OK.value());

		return ResponseEntity.ok().body(response);
	}
}
