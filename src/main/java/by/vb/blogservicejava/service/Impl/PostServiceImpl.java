package by.vb.blogservicejava.service.Impl;

import by.vb.blogservicejava.dao.PostRepository;
import by.vb.blogservicejava.dto.PostCreateUpdateDto;
import by.vb.blogservicejava.dto.PostDto;
import by.vb.blogservicejava.dto.PostFilter;
import by.vb.blogservicejava.dto.PostSort;
import by.vb.blogservicejava.exception.NotFoundResourceException;
import by.vb.blogservicejava.mapper.Impl.PostCreateUpdateMapper;
import by.vb.blogservicejava.mapper.Impl.PostMapper;
import by.vb.blogservicejava.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;
	private final PostMapper postMapper;
	private final PostCreateUpdateMapper postCreateUpdateMapper;

	@Override
	public Page<PostDto> findAllPosts(
			final PostFilter postFilter,
			final PostSort postSort, final Pageable pageable
	) {

		Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()
				, postRepository.sortConditions(postSort.getSortBy()));

		return postRepository.findAll(
						postRepository.filterConditions(postFilter.getFilterBy()),
						sortedPageable)
				.map(postMapper::mapTo);
	}

	@Override
	public Optional<PostDto> findPostById(final Long id) throws NotFoundResourceException {
		return postRepository.findById(id).map(postMapper::mapTo);
	}

	@Transactional
	@Override
	public PostDto createPost(final PostCreateUpdateDto postCreateUpdateDto) {
		return Optional.of(postCreateUpdateDto)
				.map(postCreateUpdateMapper::mapTo)
				.map(postRepository::save)
				.map(postMapper::mapTo)
				.orElseThrow();
	}


	@Transactional
	@Override
	public Optional<PostDto> updatePostById(
			final Long id,
			final PostCreateUpdateDto postCreateUpdateDto
	) {
		return postRepository.findById(id)
				.map(post -> postCreateUpdateMapper.mapFromTo(postCreateUpdateDto, post))
				.map(postRepository::save)
				.map(postMapper::mapTo);
	}

	@Transactional
	@Override
	public Optional<PostDto> deletePostById(final Long id) {
		return postRepository.findById(id).map(post -> {
			postRepository.delete(post);

			return post;
		}).map(postMapper::mapTo);
	}
}
