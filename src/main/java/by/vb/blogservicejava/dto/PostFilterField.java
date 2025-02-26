package by.vb.blogservicejava.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostFilterField {
	TITLE("title");

	private final String columnName;
}
