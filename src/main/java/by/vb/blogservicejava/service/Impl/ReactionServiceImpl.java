package by.vb.blogservicejava.service.Impl;

import by.vb.blogservicejava.dao.ReactionRepository;
import by.vb.blogservicejava.dto.Reaction.ReactionCreateUpdateDto;
import by.vb.blogservicejava.dto.Reaction.ReactionDto;
import by.vb.blogservicejava.dto.Reaction.ReactionFilterDto;
import by.vb.blogservicejava.mapper.Impl.ReactionCreateUpdateMapper;
import by.vb.blogservicejava.mapper.Impl.ReactionMapper;
import by.vb.blogservicejava.service.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {
	private final ReactionRepository reactionRepository;
	private final ReactionMapper reactionMapper;
	private final ReactionCreateUpdateMapper reactionCreateUpdateMapper;

	@Override
	public Page<ReactionDto> getAllReactions(
			final Pageable pageable,
			final ReactionFilterDto filter
	) {
		return reactionRepository.findAll(
						reactionRepository.filterConditions(filter.getFilterFields()), pageable)
				.map(reactionMapper::mapTo);
	}

	@Override
	public Optional<ReactionDto> deleteReactionById(final long id) {
		return reactionRepository.findById(id).map(reaction -> {
			reactionRepository.delete(reaction);

			return reaction;
		}).map(reactionMapper::mapTo);
	}

	@Override
	public ReactionDto createReaction(
			ReactionCreateUpdateDto reactionCreateUpdateDto
	) {
		return Optional.of(reactionCreateUpdateDto)
				.map(reactionCreateUpdateMapper::mapTo)
				.map(reactionRepository::save)
				.map(reactionMapper::mapTo)
				.orElseThrow();
	}

	@Override
	public Optional<ReactionDto> updateReactionById(
			long id,
			ReactionCreateUpdateDto reactionCreateUpdateDto
	) {
		return reactionRepository.findById(id)
				.map(reaction -> reactionCreateUpdateMapper.mapFromTo(reactionCreateUpdateDto,
						reaction))
				.map(reactionRepository::save)
				.map(reactionMapper::mapTo);
	}
}
