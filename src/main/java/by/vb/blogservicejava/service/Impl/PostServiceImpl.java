package by.vb.blogservicejava.service.Impl;

import by.vb.blogservicejava.dao.PostRepository;
import by.vb.blogservicejava.dto.*;
import by.vb.blogservicejava.mapper.Impl.PostCreateUpdateMapper;
import by.vb.blogservicejava.mapper.Impl.PostDetailedMapper;
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
	private final PostDetailedMapper postDetailedMapper;

	@Override
	public Page<PostDto> findAllPosts(
			final PostFilter postFilter,
			final PostSort postSort, final Pageable pageable
	) {

		Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()
				, postRepository.sortConditions(postSort.getSortFields()));

		return postRepository.findAll(
						postRepository.filterConditions(postFilter.getFilterFields()),
						sortedPageable)
				.map(postMapper::mapTo);
	}

	@Override
	public Optional<PostDetailedDto> findPostById(final Long id) {
		return postRepository.findById(id).map(postDetailedMapper::mapTo);
	}

	@Transactional
	@Override
	public PostDetailedDto createPost(final PostCreateUpdateDto postCreateUpdateDto) {
		return Optional.of(postCreateUpdateDto)
				.map(postCreateUpdateMapper::mapTo)
				.map(postRepository::save)
				.map(postDetailedMapper::mapTo)
				.orElseThrow();
	}

	@Transactional
	@Override
	public Optional<PostDetailedDto> updatePostById(
			final Long id,
			final PostCreateUpdateDto postCreateUpdateDto
	) {
		return postRepository.findById(id)
				.map(post -> postCreateUpdateMapper.mapFromTo(postCreateUpdateDto, post))
				.map(postRepository::save)
				.map(postDetailedMapper::mapTo);
	}

	@Transactional
	@Override
	public Optional<PostDetailedDto> deletePostById(final Long id) {
		return postRepository.findById(id).map(post -> {
			postRepository.delete(post);

			return post;
		}).map(postDetailedMapper::mapTo);
	}
}
