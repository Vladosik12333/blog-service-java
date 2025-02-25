package by.vb.blogservicejava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSort {
	private SortBy sortBy = new SortBy();

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class SortBy {
		private Boolean title;
		private Boolean description;
		private Boolean username;
		private Boolean firstName;
		private Boolean lastName;
		private Boolean createdAt;
	}
}
