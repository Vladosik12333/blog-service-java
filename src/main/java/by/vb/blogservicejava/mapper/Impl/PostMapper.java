package by.vb.blogservicejava.mapper.Impl;

import by.vb.blogservicejava.dto.Post.PostDto;
import by.vb.blogservicejava.entity.Post;
import by.vb.blogservicejava.mapper.Mapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PostMapper implements Mapper<Post, PostDto> {
	@Override
	public PostDto mapTo(@NotNull final Post fromObject) {
		Objects.requireNonNull(fromObject, "Post cannot be null");

		PostDto postDto = new PostDto();

		postDto.setId(fromObject.getId());
		postDto.setTitle(fromObject.getTitle());
		postDto.setReactionsCount(fromObject.getReactions().size());

		return postDto;
	}
}
