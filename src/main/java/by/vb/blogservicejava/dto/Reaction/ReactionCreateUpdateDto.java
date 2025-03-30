package by.vb.blogservicejava.dto.Reaction;

import by.vb.blogservicejava.entity.ReactionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReactionCreateUpdateDto {
	@NotNull(message = "reactionType cannot be null")
	private ReactionType reactionType;
	@NotNull(message = "postId cannot be null")
	@Min(message = "postId cannot be less than 1", value = 1)
	private Long postId;
}
