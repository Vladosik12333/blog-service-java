package by.vb.blogservicejava.dto.Post;

import by.vb.blogservicejava.dto.Reaction.ReactionDto;
import by.vb.blogservicejava.dto.User.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailedDto {
	private Long id;
	private String title;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	private UserDto user;
	private List<ReactionDto> reactions;
}
