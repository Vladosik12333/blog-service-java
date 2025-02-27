package by.vb.blogservicejava.service;

import by.vb.blogservicejava.dto.ReactionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReactionService {
	Page<ReactionDto> getAllReactions(final Pageable pageable);

}
