package by.vb.blogservicejava.mapper.Impl;

import by.vb.blogservicejava.dto.Reaction.ReactionDto;
import by.vb.blogservicejava.dto.User.UserDto;
import by.vb.blogservicejava.entity.Reaction;
import by.vb.blogservicejava.mapper.Mapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReactionMapper implements Mapper<Reaction, ReactionDto> {
	private final UserMapper userMapper;

	@Override
	public ReactionDto mapTo(@NotNull final Reaction fromObject) {
		Objects.requireNonNull(fromObject, "Reaction cannot be null");

		ReactionDto reactionDto = new ReactionDto();

		reactionDto.setId(fromObject.getId());
		reactionDto.setType(fromObject.getType());
		reactionDto.setCreatedAt(fromObject.getCreatedAt());
		reactionDto.setModifiedAt(fromObject.getModifiedAt());

		UserDto userDto =
				Optional.ofNullable(fromObject.getUser())
						.map(userMapper::mapTo)
						.orElse(null);

		reactionDto.setUser(userDto);

		return reactionDto;
	}
}
