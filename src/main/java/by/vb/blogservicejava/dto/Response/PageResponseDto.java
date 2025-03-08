package by.vb.blogservicejava.dto.Response;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString
public class PageResponseDto<T> extends BasicResponseDto {
	private List<T> data;
	private Metadata metadata;

	public PageResponseDto(Page<T> page) {
		this.metadata = new Metadata(page.getSize(), page.getNumber(), page.getTotalElements());
		this.data = page.getContent();
		this.message = "Success";
	}

	@Value
	private static class Metadata {
		int page;
		int size;
		long totalElements;
	}
}
