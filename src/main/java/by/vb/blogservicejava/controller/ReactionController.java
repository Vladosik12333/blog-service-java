package by.vb.blogservicejava.controller;

import by.vb.blogservicejava.dto.Reaction.ReactionCreateUpdateDto;
import by.vb.blogservicejava.dto.Reaction.ReactionDto;
import by.vb.blogservicejava.dto.Reaction.ReactionFilterDto;
import by.vb.blogservicejava.dto.Response.PageResponseDto;
import by.vb.blogservicejava.dto.Response.SuccessResponseDto;
import by.vb.blogservicejava.exception.NotFoundResourceException;
import by.vb.blogservicejava.service.ReactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Reactions", description = "The group of end-points to work with Reaction entity.")
@RestController
@RequestMapping("/reactions")
@RequiredArgsConstructor
public class ReactionController {
	private final ReactionService reactionService;

	@GetMapping()
	public PageResponseDto<ReactionDto> getAllReactions(
			final Pageable pageable, final
			ReactionFilterDto filter
	) {

		final Page<ReactionDto> reactions = reactionService.getAllReactions(pageable, filter);

		final PageResponseDto<ReactionDto> responseDto = new PageResponseDto<>(reactions);

		responseDto.setCode(HttpStatus.OK.value());

		return responseDto;
	}

	@PreAuthorize(
			"@userServiceImpl.hasOwnershipAccess(#id, T(by.vb.blogservicejava.dao.ReactionRepository))"
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponseDto<ReactionDto>> deleteReactionById(
			@PathVariable final long id
	) {
		final ReactionDto reactionDto =
				reactionService.deleteReactionById(id)
						.orElseThrow(() -> new NotFoundResourceException("Reaction not found"));

		SuccessResponseDto<ReactionDto> responseDto = new SuccessResponseDto<>();

		responseDto.setCode(HttpStatus.OK.value());
		responseDto.setData(reactionDto);

		return ResponseEntity.ok().body(responseDto);
	}

	@PostMapping()
	public ResponseEntity<SuccessResponseDto<ReactionDto>> createReaction(
			@Valid @RequestBody final ReactionCreateUpdateDto reactionCreateUpdateDto
	) {
		ReactionDto reactionDto = reactionService.createReaction(reactionCreateUpdateDto);

		SuccessResponseDto<ReactionDto> responseDto = new SuccessResponseDto<>();

		responseDto.setCode(HttpStatus.OK.value());
		responseDto.setData(reactionDto);

		return ResponseEntity.ok().body(responseDto);
	}

	@PreAuthorize(
			"@userServiceImpl.hasOwnershipAccess(#id, T(by.vb.blogservicejava.dao.ReactionRepository))"
	)
	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponseDto<ReactionDto>> updateReactionById(
			@PathVariable final long id,
			@Valid @RequestBody final ReactionCreateUpdateDto reactionCreateUpdateDto
	) {
		ReactionDto reactionDto =
				reactionService.updateReactionById(id, reactionCreateUpdateDto)
						.orElseThrow(() -> new NotFoundResourceException("Reaction not found"));

		SuccessResponseDto<ReactionDto> responseDto = new SuccessResponseDto<>();

		responseDto.setCode(HttpStatus.OK.value());
		responseDto.setData(reactionDto);

		return ResponseEntity.ok().body(responseDto);
	}
}
