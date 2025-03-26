package by.vb.blogservicejava.mapper.Impl;

import by.vb.blogservicejava.dao.PostRepository;
import by.vb.blogservicejava.dao.UserRepository;
import by.vb.blogservicejava.dto.Reaction.ReactionCreateUpdateDto;
import by.vb.blogservicejava.entity.Post;
import by.vb.blogservicejava.entity.Reaction;
import by.vb.blogservicejava.entity.User;
import by.vb.blogservicejava.exception.NotFoundResourceException;
import by.vb.blogservicejava.mapper.Mapper;
import by.vb.blogservicejava.mapper.UpdateMapper;
import by.vb.blogservicejava.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReactionCreateUpdateMapper implements Mapper<ReactionCreateUpdateDto, Reaction>, UpdateMapper<ReactionCreateUpdateDto, Reaction> {
	private final UserRepository userRepository;
	private final PostRepository postRepository;

	@Override
	public Reaction mapTo(ReactionCreateUpdateDto fromObject) {
		Objects.requireNonNull(fromObject, "ReactionCreateUpdateDto cannot be null");

		Reaction reaction = new Reaction();

		reaction.setPost(getPost(fromObject.getPostId()));
		reaction.setType(fromObject.getReactionType());
		reaction.setUser(getUser(AuthUtil.getCurrentUser().getId()));

		return reaction;
	}

	@Override
	public Reaction mapFromTo(ReactionCreateUpdateDto fromObject, Reaction toObject) {
		Objects.requireNonNull(fromObject, "ReactionCreateUpdateDto cannot be null");

		toObject.setPost(getPost(fromObject.getPostId()));
		toObject.setType(fromObject.getReactionType());

		log.info("asdasdas: {}", toObject.getPost());

		return toObject;
	}

	private User getUser(final long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundResourceException("User not found"));
	}

	private Post getPost(final long id) {
		return postRepository.findById(id)
				.orElseThrow(() -> new NotFoundResourceException("Post not found"));
	}
}
