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
	public PostDto mapToObject(@NotNull Post fromObject) {
		PostDto postDto = new PostDto();
		if (Objects.isNull(fromObject))
			return postDto;

		postDto.setId(fromObject.getId());
		postDto.setTitle(fromObject.getTitle());
		postDto.setDescription(fromObject.getDescription());
		postDto.setCreatedAt(fromObject.getCreatedAt());
		postDto.setModifiedAt(fromObject.getModifiedAt());

		UserDto userDto =
				Optional.ofNullable(fromObject.getUser()).map(userMapper::mapToObject).orElse(null);

		postDto.setUser(userDto);

		return postDto;
	}
}
