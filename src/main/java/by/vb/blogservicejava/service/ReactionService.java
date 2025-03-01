package by.vb.blogservicejava.service;

import by.vb.blogservicejava.dto.ReactionCreateUpdateDto;
import by.vb.blogservicejava.dto.ReactionDto;
import by.vb.blogservicejava.dto.ReactionFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReactionService {
	Page<ReactionDto> getAllReactions(final Pageable pageable, final ReactionFilterDto filter);

	Optional<ReactionDto> deleteReactionById(final long id);

	ReactionDto createReaction(final ReactionCreateUpdateDto reactionCreateUpdateDto);

	Optional<ReactionDto> updateReactionById(
			final long id,
			final ReactionCreateUpdateDto reactionCreateUpdateDto
	);
}
