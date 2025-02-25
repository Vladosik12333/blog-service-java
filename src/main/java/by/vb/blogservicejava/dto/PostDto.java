package by.vb.blogservicejava.dto;

import by.vb.blogservicejava.entity.Reaction;
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
public class PostDto {
	private Long id;
	private String title;
	private String description;
	private LocalDateTime createdAt;
	private UserDto user;
	private List<Reaction> reactions;
	private Integer reactionsCount;
}
