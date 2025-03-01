package by.vb.blogservicejava.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReactionFilterField {
	TYPE("type");

	private final String columnName;
}
