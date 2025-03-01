package by.vb.blogservicejava.config;

import by.vb.blogservicejava.dto.response.ErrorResponseDto;
import by.vb.blogservicejava.exception.NotFoundResourceException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ApiResponse(responseCode = "500", description = "Server Internal Error")
	public ResponseEntity<ErrorResponseDto> handleException(final Exception exception) {
		log.error("Unexpected Error. Message={}. Stack Trace={}", exception.getMessage(),
				exception.getStackTrace());
		final ErrorResponseDto errorResponseDto = new ErrorResponseDto();

		errorResponseDto.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponseDto.setMessage(exception.getMessage());

		log.error("Unexpected Error. Response={}", errorResponseDto);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ApiResponse(responseCode = "400", description = "Validation failed")
	public ResponseEntity<ErrorResponseDto> handleValidationException(
			final MethodArgumentNotValidException exception
	) {
		log.warn("Validation failed. Message={}", exception.getMessage());
		ErrorResponseDto ErrorResponseDto = new ErrorResponseDto();
		List<String> errors = new ArrayList<>();

		exception.getBindingResult()
				.getAllErrors()
				.forEach(error -> errors.add(error.getDefaultMessage()));

		ErrorResponseDto.setCode(HttpStatus.BAD_REQUEST.value());
		ErrorResponseDto.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		ErrorResponseDto.setErrors(errors);

		log.warn("Validation failed. Response={}", ErrorResponseDto);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto);
	}

	@ExceptionHandler(NotFoundResourceException.class)
	@ApiResponse(responseCode = "404", description = "Resource not found.")
	public ResponseEntity<ErrorResponseDto> handleNotFoundResourceException(
			NotFoundResourceException exception
	) {
		log.warn("Resource not found. Message={}", exception.getMessage());
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();

		errorResponseDto.setCode(HttpStatus.NOT_FOUND.value());
		errorResponseDto.setMessage(exception.getMessage());

		log.warn("Resource not found. Response={}", errorResponseDto);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(errorResponseDto);
	}
}
