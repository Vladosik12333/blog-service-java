package by.vb.blogservicejava.unit;

import by.vb.blogservicejava.dao.ReactionRepository;
import by.vb.blogservicejava.dto.Reaction.ReactionCreateUpdateDto;
import by.vb.blogservicejava.dto.Reaction.ReactionDto;
import by.vb.blogservicejava.dto.Reaction.ReactionFilterDto;
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
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

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

	private ReactionDto reactionDtoToReturn;

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

		reactionDtoToReturn =
				ReactionDto.builder().id(reaction.getId()).type(reaction.getType()).build();
	}

	@Test
	public void saveReaction() {
		ReactionCreateUpdateDto reactionDtoToCreate = ReactionCreateUpdateDto.builder().build();

		given(reactionCreateUpdateMapper.mapTo(reactionDtoToCreate)).willReturn(reaction);
		given(reactionRepository.save(reaction)).willReturn(reaction);
		given(reactionMapper.mapTo(reaction)).willReturn(reactionDtoToReturn);

		ReactionDto reactionDto =
				reactionService.createReaction(reactionDtoToCreate);

		assertThat(reactionDto).isNotNull();
		assertThat(reactionDto.getId()).isEqualTo(reaction.getId());
		assertThat(reactionDto.getType()).isEqualTo(reaction.getType());
	}

	@Test
	public void getAllReactions() {
		ReactionFilterDto reactionFilterDto = new ReactionFilterDto(new HashMap<>());
		Pageable pageable = Pageable.ofSize(1);
		Specification<Reaction> specification = (reaction, cq, cb) -> null;

		given(reactionRepository.filterConditions(reactionFilterDto.getFilterFields())).willReturn(
				specification);
		given(reactionRepository.findAll(specification, pageable)).willReturn(
				new PageImpl<>(List.of(reaction,
						reaction)));
		given(reactionMapper.mapTo(reaction)).willReturn(reactionDtoToReturn);

		Page<ReactionDto> pageReactionsDto = reactionService.getAllReactions(pageable,
				reactionFilterDto);

		assertThat(pageReactionsDto).isNotNull();
		assertThat(pageReactionsDto.getTotalElements()).isEqualTo(2);
		assertThat(pageReactionsDto.getContent().get(0).getType()).isEqualTo(
				reactionDtoToReturn.getType());
	}

	@Test
	public void deleteReaction() {
		given(reactionRepository.findById(reaction.getId())).willReturn(
				Optional.of(reaction));
		doNothing().when(reactionRepository).delete(reaction);
		given(reactionMapper.mapTo(reaction)).willReturn(reactionDtoToReturn);

		Optional<ReactionDto> reactionDto = reactionService.deleteReactionById(reaction.getId());

		assertThat(reactionDto).isNotEmpty();
		assertThat(reactionDto.get().getType()).isEqualTo(reactionDtoToReturn.getType());
	}

	@Test
	public void updateReaction() {
		ReactionCreateUpdateDto reactionDtoToUpdate =
				ReactionCreateUpdateDto.builder()
						.reactionType(ReactionType.DISLIKE)
						.postId(1L)
						.build();
		Reaction updatedReaction =
				Reaction.builder()
						.user(reaction.getUser())
						.type(reactionDtoToUpdate.getReactionType())
						.post(reaction.getPost())
						.build();
		ReactionDto updatedReactionToReturnDto =
				ReactionDto.builder()
						.type(updatedReaction.getType())
						.build();

		given(reactionRepository.findById(reaction.getId())).willReturn(Optional.of(reaction));
		given(reactionCreateUpdateMapper.mapFromTo(reactionDtoToUpdate, reaction)).willReturn(
				updatedReaction);
		given(reactionRepository.save(updatedReaction)).willReturn(updatedReaction);
		given(reactionMapper.mapTo(updatedReaction)).willReturn(updatedReactionToReturnDto);

		Optional<ReactionDto> updatedReactionDto =
				reactionService.updateReactionById(reaction.getId(), reactionDtoToUpdate);

		assertThat(updatedReactionDto).isNotEmpty();
		assertThat(updatedReactionDto.get().getType()).isEqualTo(updatedReactionToReturnDto.getType());
	}
}
