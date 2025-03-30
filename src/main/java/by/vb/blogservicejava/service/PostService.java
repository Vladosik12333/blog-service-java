package by.vb.blogservicejava.service;

import by.vb.blogservicejava.dto.Post.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostService {
	/**
	 * Retrieve all posts
	 *
	 * @param postFilterDto filtering settings
	 * @param postSortDto sorting settings
	 * @param pageable pagination settings
	 * @return all {@link PostDto}
	 */
	Page<PostDto> findAllPosts(
			final PostFilterDto postFilterDto,
			final PostSortDto postSortDto,
			final Pageable pageable
	);

	/**
	 * Return one post based on ID
	 *
	 * @param id post ID
	 * @return {@link PostDetailedDto}
	 */
	Optional<PostDetailedDto> findPostById(final Long id);

	/**
	 * Create post
	 *
	 * @param postCreateUpdateDto post DTO
	 * @return created {@link PostDetailedDto}
	 */
	PostDetailedDto createPost(final PostCreateUpdateDto postCreateUpdateDto);

	/**
	 * Update post based on ID
	 *
	 * @param id post ID
	 * @param postCreateUpdateDto post DTO
	 * @return updated {@link PostDetailedDto}
	 */
	Optional<PostDetailedDto> updatePostById(final Long id, final PostCreateUpdateDto postCreateUpdateDto);

	/**
	 * Delete post based on ID
	 *
	 * @param id post ID
	 * @return deleted {@link PostDetailedDto}
	 */
	Optional<PostDetailedDto> deletePostById(final Long id);
}
