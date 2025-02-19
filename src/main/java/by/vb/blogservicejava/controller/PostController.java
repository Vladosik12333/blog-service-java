package by.vb.blogservicejava.controller;

import by.vb.blogservicejava.dto.PostDto;
import by.vb.blogservicejava.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;


// implement list for sorting, filtering and pagination
	@GetMapping
	public ResponseEntity<List<PostDto>> findAll () {
		List<PostDto> postDtoList = postService.findAllPosts();

		return ResponseEntity.ok().body(postDtoList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> findById(@PathVariable final Long id) {
		PostDto postDto =
				postService.findPostById(id).orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND));

		return ResponseEntity.ok().body(postDto);
	}
}
