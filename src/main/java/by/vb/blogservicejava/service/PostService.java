package by.vb.blogservicejava.service;

import by.vb.blogservicejava.dto.PostCreateUpdateDto;
import by.vb.blogservicejava.dto.PostDto;
import by.vb.blogservicejava.exception.NotFoundResourceException;

import java.util.List;
import java.util.Optional;

public interface PostService {
	List<PostDto> findAllPosts();

	Optional<PostDto> findPostById(final Long id);

	PostDto createPost(final PostCreateUpdateDto postCreateUpdateDto) throws
			NotFoundResourceException;

	Optional<PostDto> updatePostById(final Long id, final PostCreateUpdateDto postCreateUpdateDto);

	Optional<PostDto> deletePostById(final Long id);
}
