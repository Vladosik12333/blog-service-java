package by.vb.blogservicejava.dto.Reaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReactionFilterField {
	TYPE("type");

	private final String columnName;
}
