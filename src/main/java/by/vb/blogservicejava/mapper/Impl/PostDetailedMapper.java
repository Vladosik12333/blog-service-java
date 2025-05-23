package by.vb.blogservicejava.mapper.Impl;

import by.vb.blogservicejava.dto.Post.PostDetailedDto;
import by.vb.blogservicejava.dto.Reaction.ReactionDto;
import by.vb.blogservicejava.dto.User.UserDto;
import by.vb.blogservicejava.entity.Post;
import by.vb.blogservicejava.mapper.Mapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostDetailedMapper implements Mapper<Post, PostDetailedDto> {
	private final UserMapper userMapper;
	private final ReactionMapper reactionMapper;

	@Override
	public PostDetailedDto mapTo(@NotNull final Post fromObject) {
		Objects.requireNonNull(fromObject, "Post cannot be null");

		PostDetailedDto postDetailedDto = new PostDetailedDto();

		postDetailedDto.setId(fromObject.getId());
		postDetailedDto.setTitle(fromObject.getTitle());
		postDetailedDto.setDescription(fromObject.getDescription());
		postDetailedDto.setCreatedAt(fromObject.getCreatedAt());
		postDetailedDto.setModifiedAt(fromObject.getModifiedAt());

		List<ReactionDto> reactionDtoList =
				fromObject.getReactions().stream().map(reactionMapper::mapTo).toList();

		postDetailedDto.setReactions(reactionDtoList);

		UserDto userDto =
				Optional.ofNullable(fromObject.getUser()).map(userMapper::mapTo).orElse(null);

		postDetailedDto.setUser(userDto);

		return postDetailedDto;
	}
}
