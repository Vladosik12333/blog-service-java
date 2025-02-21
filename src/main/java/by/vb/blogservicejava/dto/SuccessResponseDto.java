package by.vb.blogservicejava.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class SuccessResponseDto<T> extends BasicResponseDto {
	private T data;

	public SuccessResponseDto() {
		super();
		this.message = "Success";
	}
}
