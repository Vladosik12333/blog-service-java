package by.vb.blogservicejava.controller;

import by.vb.blogservicejava.dto.ReactionDto;
import by.vb.blogservicejava.dto.response.PageResponseDto;
import by.vb.blogservicejava.service.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reactions")
@RequiredArgsConstructor
public class ReactionController {
	private final ReactionService reactionService;

	@GetMapping()
	public PageResponseDto<ReactionDto> getAllReactions(final Pageable pageable) {

		final Page<ReactionDto> reactions = reactionService.getAllReactions(pageable);

		final PageResponseDto<ReactionDto> responseDto = new PageResponseDto<>(reactions);

		responseDto.setCode(HttpStatus.OK.value());

		return responseDto;
	}
}
