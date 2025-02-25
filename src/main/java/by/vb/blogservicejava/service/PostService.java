package by.vb.blogservicejava.service;

import by.vb.blogservicejava.dto.PostCreateUpdateDto;
import by.vb.blogservicejava.dto.PostDto;
import by.vb.blogservicejava.dto.PostFilter;
import by.vb.blogservicejava.dto.PostSort;
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

	Optional<PostDto> findPostById(final Long id) throws NotFoundResourceException;

	PostDto createPost(final PostCreateUpdateDto postCreateUpdateDto) throws
			NotFoundResourceException;

	Optional<PostDto> updatePostById(final Long id, final PostCreateUpdateDto postCreateUpdateDto)
			throws NotFoundResourceException;

	Optional<PostDto> deletePostById(final Long id) throws NotFoundResourceException;
}
