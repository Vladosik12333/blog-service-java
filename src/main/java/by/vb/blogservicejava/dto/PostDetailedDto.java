package by.vb.blogservicejava.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailedDto {
	private Long id;
	private String title;
	private String description;
	private LocalDateTime createdAt;
	private UserDto user;
	private Integer reactionsCount;
}
