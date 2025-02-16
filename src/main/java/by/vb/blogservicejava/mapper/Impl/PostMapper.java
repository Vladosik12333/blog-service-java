package by.vb.blogservicejava.mapper.Impl;

import by.vb.blogservicejava.dto.PostDto;
import by.vb.blogservicejava.entity.Post;
import by.vb.blogservicejava.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements Mapper<Post, PostDto> {
	@Override
	public PostDto mapTo(Post from) {
		PostDto postDto = new PostDto();

		postDto.setId(from.getId());
		postDto.setTitle(from.getTitle());
		postDto.setDescription(from.getDescription());

		return postDto;
	}
}
