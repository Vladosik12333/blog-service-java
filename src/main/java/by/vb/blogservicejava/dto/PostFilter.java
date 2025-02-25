package by.vb.blogservicejava.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostFilter {
	private FilterBy filterBy = new FilterBy();

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FilterBy {
		private String title;
	}
}
