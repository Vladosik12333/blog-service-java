package by.vb.blogservicejava.service;

import by.vb.blogservicejava.dto.Post.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface PostService {
	Page<PostDto> findAllPosts(
			final PostFilterDto postFilterDto,
			final PostSortDto postSortDto,
			final Pageable pageable
	);

	Optional<PostDetailedDto> findPostById(final Long id);

	PostDetailedDto createPost(final PostCreateUpdateDto postCreateUpdateDto);

	Optional<PostDetailedDto> updatePostById(final Long id, final PostCreateUpdateDto postCreateUpdateDto);

	Optional<PostDetailedDto> deletePostById(final Long id);
}
