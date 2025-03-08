package by.vb.blogservicejava.dto.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSortDto {
	private Map<PostSortField, Boolean> sortFields = new HashMap<>();
}
