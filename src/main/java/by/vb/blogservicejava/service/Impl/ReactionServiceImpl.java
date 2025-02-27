package by.vb.blogservicejava.service.Impl;

import by.vb.blogservicejava.dao.ReactionRepository;
import by.vb.blogservicejava.dto.ReactionDto;
import by.vb.blogservicejava.mapper.Impl.ReactionMapper;
import by.vb.blogservicejava.service.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {
	private final ReactionRepository reactionRepository;
	private final ReactionMapper reactionMapper;

	@Override
	public Page<ReactionDto> getAllReactions(final Pageable pageable) {
		return reactionRepository.findAll(pageable).map(reactionMapper::mapTo);
	}
}
