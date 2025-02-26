package by.vb.blogservicejava.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostSortField {
	TITLE("title"), DESCRIPTION("description"), USERNAME("user.username"), FIRST_NAME(
			"user.firstName"), LAST_NAME("user.lastName"), CREATED_AT("createdAt");

	private final String columnName;
	}
