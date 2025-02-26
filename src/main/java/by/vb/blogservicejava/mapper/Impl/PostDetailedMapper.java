package by.vb.blogservicejava.mapper.Impl;

import by.vb.blogservicejava.dto.PostDetailedDto;
import by.vb.blogservicejava.dto.UserDto;
import by.vb.blogservicejava.entity.Post;
import by.vb.blogservicejava.mapper.Mapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostDetailedMapper implements Mapper<Post, PostDetailedDto> {
	private final UserMapper userMapper;

	@Override
	public PostDetailedDto mapTo(@NotNull final Post fromObject) {
		Objects.requireNonNull(fromObject, "Post cannot be null");

		PostDetailedDto postDetailedDto = new PostDetailedDto();

		postDetailedDto.setId(fromObject.getId());
		postDetailedDto.setTitle(fromObject.getTitle());
		postDetailedDto.setDescription(fromObject.getDescription());
		postDetailedDto.setCreatedAt(fromObject.getCreatedAt());
		postDetailedDto.setReactionsCount(fromObject.getReactions().size());

		UserDto userDto =
				Optional.ofNullable(fromObject.getUser()).map(userMapper::mapTo).orElse(null);

		postDetailedDto.setUser(userDto);

		return postDetailedDto;
	}
}
