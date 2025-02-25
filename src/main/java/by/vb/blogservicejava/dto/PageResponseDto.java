package by.vb.blogservicejava.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class PageResponseDto<T> extends BasicResponseDto {
	private List<T> data;
	private Metadata metadata;

	public static <T> PageResponseDto<T> of(Page<T> page) {
		var metadata = new Metadata(page.getSize(), page.getNumber(), page.getTotalElements());
		return new PageResponseDto<T>(page.getContent(), metadata);
	}

	@Value
	private static class Metadata {
		int page;
		int size;
		long totalElements;
	}
}
