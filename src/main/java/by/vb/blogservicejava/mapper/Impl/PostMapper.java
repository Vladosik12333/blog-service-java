package by.vb.blogservicejava.mapper.Impl;

import by.vb.blogservicejava.dto.PostDto;
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
public class PostMapper implements Mapper<Post, PostDto> {
	private final UserMapper userMapper;

	@Override
	public PostDto mapTo(@NotNull final Post fromObject) {
		Objects.requireNonNull(fromObject, "Post cannot be null");

		PostDto postDto = new PostDto();

		postDto.setId(fromObject.getId());
		postDto.setTitle(fromObject.getTitle());
		postDto.setDescription(fromObject.getDescription());
		postDto.setCreatedAt(fromObject.getCreatedAt());
		postDto.setReactionsCount(fromObject.getReactions().size());

		UserDto userDto =
				Optional.ofNullable(fromObject.getUser()).map(userMapper::mapTo).orElse(null);

		postDto.setUser(userDto);

//		postDto.setReactions(fromObject.getReactions());

//		user mapper


		return postDto;
	}
}
