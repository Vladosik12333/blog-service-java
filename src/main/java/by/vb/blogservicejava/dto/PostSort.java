package by.vb.blogservicejava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSort {
	private Map<PostSortField, Boolean> sortFields = new HashMap<>();
}
