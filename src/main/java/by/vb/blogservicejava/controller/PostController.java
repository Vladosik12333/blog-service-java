package by.vb.blogservicejava.controller;

import by.vb.blogservicejava.dto.PostCreateUpdateDto;
import by.vb.blogservicejava.dto.PostDto;
import by.vb.blogservicejava.dto.SuccessResponseDto;
import by.vb.blogservicejava.exception.NotFoundResourceException;
import by.vb.blogservicejava.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	// implement list for sorting, filtering and pagination
	@GetMapping
	public ResponseEntity<SuccessResponseDto<List<PostDto>>> findAll() {
		SuccessResponseDto<List<PostDto>> successResponseDto = new SuccessResponseDto<>();

		List<PostDto> postDtoList = postService.findAllPosts();

		successResponseDto.setCode(HttpStatus.OK.value());
		successResponseDto.setData(postDtoList);

		return ResponseEntity.ok().body(successResponseDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponseDto<PostDto>> findById(@PathVariable final Long id)  throws NotFoundResourceException {
		SuccessResponseDto<PostDto> successResponseDto = new SuccessResponseDto<>();

		PostDto postDto = postService.findPostById(id)
				.orElseThrow(this::generateNotFoundException);

		successResponseDto.setCode(HttpStatus.OK.value());
		successResponseDto.setData(postDto);

		return ResponseEntity.ok().body(successResponseDto);
	}

	@PostMapping
	public ResponseEntity<SuccessResponseDto<PostDto>> create(
			@Valid @RequestBody final PostCreateUpdateDto postCreateUpdateDto
	) throws NotFoundResourceException {
		SuccessResponseDto<PostDto> successResponseDto = new SuccessResponseDto<>();

		PostDto postDto = postService.createPost(postCreateUpdateDto);

		successResponseDto.setCode(HttpStatus.OK.value());
		successResponseDto.setData(postDto);

		return ResponseEntity.ok().body(successResponseDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponseDto<PostDto>> updateById(
			@PathVariable final Long id,
			@Valid @RequestBody final PostCreateUpdateDto postCreateUpdateDto
	) {
		SuccessResponseDto<PostDto> successResponseDto = new SuccessResponseDto<>();

		PostDto postDto = postService.updatePostById(id, postCreateUpdateDto)
				.orElseThrow(this::generateNotFoundException);

		successResponseDto.setCode(HttpStatus.OK.value());
		successResponseDto.setData(postDto);

		return ResponseEntity.ok().body(successResponseDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponseDto<PostDto>> deleteById(@PathVariable final Long id) {
		SuccessResponseDto<PostDto> successResponseDto = new SuccessResponseDto<>();

		PostDto postDto = postService.deletePostById(id).orElseThrow(this::generateNotFoundException);

		successResponseDto.setCode(HttpStatus.OK.value());
		successResponseDto.setData(postDto);

		return ResponseEntity.ok().body(successResponseDto);
	}

	private NotFoundResourceException generateNotFoundException() {
		return new NotFoundResourceException("Post by provided ID not found");
	}
}
