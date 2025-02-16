package by.vb.blogservicejava.service;

import by.vb.blogservicejava.dto.PostDto;

import java.util.Optional;

public interface PostService {
	Optional<PostDto> findPostById(final Long id);
}
