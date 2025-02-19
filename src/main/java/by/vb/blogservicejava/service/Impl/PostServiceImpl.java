package by.vb.blogservicejava.service.Impl;

import by.vb.blogservicejava.dao.PostRepository;
import by.vb.blogservicejava.dto.PostDto;
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

	@Override
	public List<PostDto> findAllPosts() {
		return postRepository.findAll().stream().map(postMapper::mapToObject).toList();
	}

	@Override
	public Optional<PostDto> findPostById(final Long id) {
		return postRepository.findById(id).map(postMapper::mapToObject);
	}
}
