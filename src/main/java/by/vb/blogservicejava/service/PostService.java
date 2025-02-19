package by.vb.blogservicejava.service;

import by.vb.blogservicejava.dto.PostDto;

import java.util.List;
import java.util.Optional;

public interface PostService {
	List<PostDto> findAllPosts();

	Optional<PostDto> findPostById(final Long id);
}
