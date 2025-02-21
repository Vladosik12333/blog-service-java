package by.vb.blogservicejava.service.Impl;

import by.vb.blogservicejava.dao.PostRepository;
import by.vb.blogservicejava.dto.PostCreateUpdateDto;
import by.vb.blogservicejava.dto.PostDto;
import by.vb.blogservicejava.entity.Post;
import by.vb.blogservicejava.exception.NotFoundResourceException;
import by.vb.blogservicejava.mapper.Impl.PostCreateUpdateMapper;
import by.vb.blogservicejava.mapper.Impl.PostMapper;
import by.vb.blogservicejava.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;
	private final PostMapper postMapper;
	private final PostCreateUpdateMapper postCreateUpdateMapper;

	@Override
	public List<PostDto> findAllPosts() {
		return postRepository.findAll().stream().map(postMapper::mapTo).toList();
	}

	@Override
	public Optional<PostDto> findPostById(final Long id) {
		return postRepository.findById(id).map(postMapper::mapTo);
	}

	@Transactional
	@Override
	public PostDto createPost(final PostCreateUpdateDto postCreateUpdateDto) throws
			NotFoundResourceException {
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
