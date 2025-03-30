package by.vb.blogservicejava.config;

import by.vb.blogservicejava.dto.Response.ErrorResponseDto;
import by.vb.blogservicejava.exception.NotAuthorizedException;
import by.vb.blogservicejava.exception.NotFoundResourceException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@ApiResponses(
		value = {@ApiResponse(responseCode = "400", description = "Bad Request."),
				@ApiResponse(responseCode = "401", description = "The user is not authenticated."),
				@ApiResponse(responseCode = "403", description = "The user does not have access."),
				@ApiResponse(responseCode = "404", description = "Resource not found."),
				@ApiResponse(responseCode = "500", description = "Server Internal Error.")
		}
)
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleException(final Exception exception) {
		log.error("Unexpected Error. Message={} Stack={}", exception.getMessage(),
				exception.getStackTrace());
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();

		errorResponseDto.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponseDto.setMessage(exception.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponseDto> handleBadRequestException(
			final BadRequestException exception
	) {
		log.warn("BadRequestException. Message={}. Stack={}", exception.getMessage(),
				exception.getStackTrace());
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();

		errorResponseDto.setCode(HttpStatus.BAD_REQUEST.value());
		errorResponseDto.setMessage(exception.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDto> handleValidationException(
			final MethodArgumentNotValidException exception
	) {
		log.warn("MethodArgumentNotValidException. Message={} Stack={}", exception.getMessage(),
				exception.getStackTrace());
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
	public ResponseEntity<ErrorResponseDto> handleNotFoundResourceException(
			final NotFoundResourceException exception
	) {
		log.warn("NotFoundResourceException. Message={} Stack={}", exception.getMessage(),
				exception.getStackTrace());
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();

		errorResponseDto.setCode(HttpStatus.NOT_FOUND.value());
		errorResponseDto.setMessage(exception.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(errorResponseDto);
	}

	@ExceptionHandler(NotAuthorizedException.class)
	public ResponseEntity<ErrorResponseDto> handleNotAuthorizedException(
			final NotAuthorizedException exception
	) {
		log.warn("NotAuthorizedException. Message={} Stack={}", exception.getMessage(),
				exception.getStackTrace());
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();

		errorResponseDto.setCode(HttpStatus.UNAUTHORIZED.value());
		errorResponseDto.setMessage(exception.getMessage());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(errorResponseDto);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResponseDto> handleBadCredentialsException(
			final BadCredentialsException exception
	) {
		log.warn("BadCredentialsException. Message={} Stack={}", exception.getMessage(),
				exception.getStackTrace());
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();

		errorResponseDto.setCode(HttpStatus.UNAUTHORIZED.value());
		errorResponseDto.setMessage("The username or password is incorrect.");

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(errorResponseDto);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponseDto> handleAccessDeniedException(
			final AccessDeniedException exception
	) {
		log.warn("AccessDeniedException. Message={} Stack={}", exception.getMessage(),
				exception.getStackTrace());
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();

		errorResponseDto.setCode(HttpStatus.FORBIDDEN.value());
		errorResponseDto.setMessage("The user does not have access to this resource.");

		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(errorResponseDto);
	}

	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<ErrorResponseDto> handleSignatureException(
			final SignatureException exception
	) {
		log.warn("SignatureException. Message={} Stack={}", exception.getMessage(),
				exception.getStackTrace());
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();

		errorResponseDto.setCode(HttpStatus.FORBIDDEN.value());
		errorResponseDto.setMessage("The JWT token is invalid.");

		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(errorResponseDto);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<ErrorResponseDto> handleExpiredJwtException(
			final ExpiredJwtException exception
	) {
		log.warn("ExpiredJwtException. Message={} Stack={}", exception.getMessage(),
				exception.getStackTrace());
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();

		errorResponseDto.setCode(HttpStatus.FORBIDDEN.value());
		errorResponseDto.setMessage("The JWT token is expired.");

		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(errorResponseDto);
	}
}
