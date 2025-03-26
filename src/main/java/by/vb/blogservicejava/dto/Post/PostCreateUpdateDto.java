package by.vb.blogservicejava.dto.Post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateUpdateDto {
	@Size(max = 128, message = "title length cannot exceed 128 symbols")
	@NotBlank(message = "title cannot be blank")
	private String title;
	private String description;
}
