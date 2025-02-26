package by.vb.blogservicejava.mapper.Impl;

import by.vb.blogservicejava.dao.UserRepository;
import by.vb.blogservicejava.dto.PostCreateUpdateDto;
import by.vb.blogservicejava.entity.Post;
import by.vb.blogservicejava.entity.User;
import by.vb.blogservicejava.exception.NotFoundResourceException;
import by.vb.blogservicejava.mapper.Mapper;
import by.vb.blogservicejava.mapper.UpdateMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PostCreateUpdateMapper implements Mapper<PostCreateUpdateDto, Post>,
		UpdateMapper<PostCreateUpdateDto, Post> {
	private final UserRepository userRepository;

	@Override
	public Post mapTo(@NotNull final PostCreateUpdateDto fromObject)  {
		Objects.requireNonNull(fromObject, "PostCreateEditDto cannot be null");

		Post post = new Post();

		post.setTitle(fromObject.getTitle());
		post.setDescription(fromObject.getDescription());
		post.setUser(findUser(fromObject.getUserId()));

		return post;
	}

	@Override
	public Post mapFromTo(@NotNull final PostCreateUpdateDto fromObject,
	                      @NotNull final Post toObject)  {
		Objects.requireNonNull(fromObject, "PostCreateEditDto cannot be null");
		Objects.requireNonNull(toObject, "Post cannot be null");

		toObject.setTitle(fromObject.getTitle());
		toObject.setDescription(fromObject.getDescription());
		toObject.setUser(findUser(fromObject.getUserId()));

		return toObject;
	}

	private User findUser(final Long id)  {
		return userRepository.findById(id)
				.orElseThrow(
						() -> new NotFoundResourceException(
								"User by ID=%d not found".formatted(id)));
	}
}
