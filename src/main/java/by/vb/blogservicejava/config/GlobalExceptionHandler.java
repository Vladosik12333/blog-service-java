package by.vb.blogservicejava.config;

import by.vb.blogservicejava.dto.ErrorResponseDto;
import by.vb.blogservicejava.exception.NotFoundResourceException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ApiResponse(responseCode = "500", description = "Server Internal Error")
	public ResponseEntity<ErrorResponseDto> handleException(final Exception exception) {
		final ErrorResponseDto ErrorResponseDto = new ErrorResponseDto();

		ErrorResponseDto.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		ErrorResponseDto.setMessage(exception.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponseDto);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ApiResponse(responseCode = "400", description = "Validation failed")
	public ResponseEntity<ErrorResponseDto> handleValidationException(
			final MethodArgumentNotValidException exception
	) {
		ErrorResponseDto ErrorResponseDto = new ErrorResponseDto();
		List<String> errors = new ArrayList<>();

		exception.getBindingResult()
				.getAllErrors()
				.forEach(error -> errors.add(error.getDefaultMessage()));

		ErrorResponseDto.setCode(HttpStatus.BAD_REQUEST.value());
		ErrorResponseDto.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		ErrorResponseDto.setErrors(errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto);
	}

	@ExceptionHandler(NotFoundResourceException.class)
	@ApiResponse(responseCode = "404", description = "Resource not found.")
	public ResponseEntity<ErrorResponseDto> handleNotFoundResourceException(
			NotFoundResourceException exception
	) {
		ErrorResponseDto ErrorResponseDto = new ErrorResponseDto();

		ErrorResponseDto.setCode(HttpStatus.NOT_FOUND.value());
		ErrorResponseDto.setMessage(exception.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ErrorResponseDto);
	}
}
