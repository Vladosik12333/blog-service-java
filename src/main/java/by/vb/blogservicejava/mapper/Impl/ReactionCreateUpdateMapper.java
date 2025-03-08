package by.vb.blogservicejava.mapper.Impl;

import by.vb.blogservicejava.dao.UserRepository;
import by.vb.blogservicejava.dto.Reaction.ReactionCreateUpdateDto;
import by.vb.blogservicejava.entity.Reaction;
import by.vb.blogservicejava.entity.User;
import by.vb.blogservicejava.exception.NotFoundResourceException;
import by.vb.blogservicejava.mapper.Mapper;
import by.vb.blogservicejava.mapper.UpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ReactionCreateUpdateMapper implements Mapper<ReactionCreateUpdateDto, Reaction>,
		UpdateMapper<ReactionCreateUpdateDto, Reaction> {
	private final UserRepository userRepository;

	@Override
	public Reaction mapTo(ReactionCreateUpdateDto fromObject) {
		Objects.requireNonNull(fromObject, "ReactionCreateUpdateDto cannot be null");

		Reaction reaction = new Reaction();

		reaction.setType(fromObject.getReactionType());
		reaction.setUser(getUser(fromObject.getUserId()));

		return reaction;
	}

	@Override
	public Reaction mapFromTo(ReactionCreateUpdateDto fromObject, Reaction toObject) {
		Objects.requireNonNull(fromObject, "ReactionCreateUpdateDto cannot be null");

		toObject.setType(fromObject.getReactionType());
		toObject.setUser(getUser(fromObject.getUserId()));

		return toObject;
	}

	private User getUser(final long id) {
		return userRepository.findById(id).orElseThrow(
				() -> new NotFoundResourceException("User not found"));
	}

}
