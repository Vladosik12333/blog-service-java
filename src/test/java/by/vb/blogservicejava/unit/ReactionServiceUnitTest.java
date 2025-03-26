package by.vb.blogservicejava.unit;

import by.vb.blogservicejava.dao.ReactionRepository;
import by.vb.blogservicejava.dto.Reaction.ReactionCreateUpdateDto;
import by.vb.blogservicejava.dto.Reaction.ReactionDto;
import by.vb.blogservicejava.entity.*;
import by.vb.blogservicejava.mapper.Impl.ReactionCreateUpdateMapper;
import by.vb.blogservicejava.mapper.Impl.ReactionMapper;
import by.vb.blogservicejava.service.Impl.ReactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ReactionServiceUnitTest {
	@Mock
	private ReactionRepository reactionRepository;

	@Mock
	private ReactionMapper reactionMapper;

	@Mock
	private ReactionCreateUpdateMapper reactionCreateUpdateMapper;

	@InjectMocks
	private ReactionServiceImpl reactionService;

	private Reaction reaction;

	@BeforeEach
	public void setUpData() {
		User user = User.builder()
				.username("test login")
				.password("test password")
				.role(RoleType.USER)
				.build();
		user.setId(1L);

		Post post = Post.builder().title("title of the test post").build();
		post.setId(1L);
		post.setUser(user);

		reaction = Reaction.builder().type(ReactionType.LIKE).build();
		reaction.setId(1L);
		reaction.setUser(user);
		reaction.setPost(post);
	}

	@Test
	public void saveReaction() {
		ReactionCreateUpdateDto reactionDtoToCreate = ReactionCreateUpdateDto.builder().build();
		ReactionDto reactionDtoToReturn =
				ReactionDto.builder().id(reaction.getId()).type(reaction.getType()).build();

		given(reactionCreateUpdateMapper.mapTo(reactionDtoToCreate)).willReturn(reaction);
		given(reactionRepository.save(reaction)).willReturn(reaction);
		given(reactionMapper.mapTo(reaction)).willReturn(reactionDtoToReturn);

		ReactionDto reactionDto =
				reactionService.createReaction(reactionDtoToCreate);

		assertThat(reactionDto).isNotNull();
		assertThat(reactionDto.getId()).isEqualTo(reaction.getId());
		assertThat(reactionDto.getType()).isEqualTo(reaction.getType());
	}
}
