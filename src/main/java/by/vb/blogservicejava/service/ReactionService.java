package by.vb.blogservicejava.service;

import by.vb.blogservicejava.dto.Reaction.ReactionCreateUpdateDto;
import by.vb.blogservicejava.dto.Reaction.ReactionDto;
import by.vb.blogservicejava.dto.Reaction.ReactionFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReactionService {
	/**
	 * Retrieves all reactions
	 *
	 * @param pageable pagination settings
	 * @param filter filtering settings
	 * @return all {@link ReactionDto}
	 */
	Page<ReactionDto> getAllReactions(final Pageable pageable, final ReactionFilterDto filter);

	/**
	 * Retrieves one reaction based on ID
	 *
	 * @param id reaction ID
	 * @return {@link ReactionDto}
	 */
	Optional<ReactionDto> deleteReactionById(final long id);

	/**
	 * Create reaction
	 *
	 * @param reactionCreateUpdateDto reaction DTO
	 * @return created {@link ReactionDto}
	 */
	ReactionDto createReaction(final ReactionCreateUpdateDto reactionCreateUpdateDto);

	/**
	 * Update reaction based on ID
	 *
	 * @param id reaction ID
	 * @param reactionCreateUpdateDto reaction DTO
	 * @return updated {@link ReactionDto}
	 */
	Optional<ReactionDto> updateReactionById(
			final long id,
			final ReactionCreateUpdateDto reactionCreateUpdateDto
	);
}
