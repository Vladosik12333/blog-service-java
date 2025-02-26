package by.vb.blogservicejava.service;

import by.vb.blogservicejava.dto.*;
import by.vb.blogservicejava.exception.NotFoundResourceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface PostService {
	Page<PostDto> findAllPosts(
			final PostFilter postFilter,
			final PostSort postSort,
			final Pageable pageable
	);

	Optional<PostDetailedDto> findPostById(final Long id) throws NotFoundResourceException;

	PostDetailedDto createPost(final PostCreateUpdateDto postCreateUpdateDto) throws
			NotFoundResourceException;

	Optional<PostDetailedDto> updatePostById(final Long id, final PostCreateUpdateDto postCreateUpdateDto)
			throws NotFoundResourceException;

	Optional<PostDetailedDto> deletePostById(final Long id) throws NotFoundResourceException;
}
