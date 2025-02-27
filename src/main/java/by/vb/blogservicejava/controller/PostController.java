package by.vb.blogservicejava.controller;

import by.vb.blogservicejava.dto.*;
import by.vb.blogservicejava.dto.response.PageResponseDto;
import by.vb.blogservicejava.dto.response.SuccessResponseDto;
import by.vb.blogservicejava.exception.NotFoundResourceException;
import by.vb.blogservicejava.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	@GetMapping
	public ResponseEntity<PageResponseDto<PostDto>> findAll(
			final PostFilter postFilter,
			final PostSort postSort,
			final Pageable pageable
	) {
		Page<PostDto> postDtoPage = postService.findAllPosts(postFilter, postSort, pageable);

		PageResponseDto<PostDto> pageResponseDto = new PageResponseDto<>(postDtoPage);

		pageResponseDto.setCode(HttpStatus.OK.value());

		return ResponseEntity.ok().body(pageResponseDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponseDto<PostDetailedDto>> findById(
			@PathVariable final Long id
	) {
		SuccessResponseDto<PostDetailedDto> successResponseDto = new SuccessResponseDto<>();

		PostDetailedDto postDetailedDto = postService.findPostById(id)
				.orElseThrow(this::generateNotFoundException);

		successResponseDto.setCode(HttpStatus.OK.value());
		successResponseDto.setData(postDetailedDto);

		return ResponseEntity.ok().body(successResponseDto);
	}

	@PostMapping
	public ResponseEntity<SuccessResponseDto<PostDetailedDto>> create(
			@Valid @RequestBody final PostCreateUpdateDto postCreateUpdateDto
	) {
		SuccessResponseDto<PostDetailedDto> successResponseDto = new SuccessResponseDto<>();

		PostDetailedDto postDetailedDto = postService.createPost(postCreateUpdateDto);

		successResponseDto.setCode(HttpStatus.OK.value());
		successResponseDto.setData(postDetailedDto);

		return ResponseEntity.ok().body(successResponseDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponseDto<PostDetailedDto>> updateById(
			@PathVariable final Long id,
			@Valid @RequestBody final PostCreateUpdateDto postCreateUpdateDto
	) {
		SuccessResponseDto<PostDetailedDto> successResponseDto = new SuccessResponseDto<>();

		PostDetailedDto postDetailedDto = postService.updatePostById(id, postCreateUpdateDto)
				.orElseThrow(this::generateNotFoundException);

		successResponseDto.setCode(HttpStatus.OK.value());
		successResponseDto.setData(postDetailedDto);

		return ResponseEntity.ok().body(successResponseDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponseDto<PostDetailedDto>> deleteById(
			@PathVariable final Long id
	) {
		SuccessResponseDto<PostDetailedDto> successResponseDto = new SuccessResponseDto<>();

		PostDetailedDto postDetailedDto = postService.deletePostById(id)
				.orElseThrow(this::generateNotFoundException);

		successResponseDto.setCode(HttpStatus.OK.value());
		successResponseDto.setData(postDetailedDto);

		return ResponseEntity.ok().body(successResponseDto);
	}

	private NotFoundResourceException generateNotFoundException() {
		return new NotFoundResourceException("Post by provided ID not found");
	}
}
