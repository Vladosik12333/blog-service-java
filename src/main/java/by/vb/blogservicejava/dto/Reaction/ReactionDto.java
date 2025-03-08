package by.vb.blogservicejava.dto.Reaction;

import by.vb.blogservicejava.dto.User.UserDto;
import by.vb.blogservicejava.entity.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReactionDto {
	private Long id;
	private ReactionType type;
	private LocalDateTime modifiedAt;
	private LocalDateTime createdAt;
	private UserDto user;
}
