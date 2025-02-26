package by.vb.blogservicejava.dto;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostFilter {
	private Map<PostFilterField, String> filterFields = new HashMap<>();
}
