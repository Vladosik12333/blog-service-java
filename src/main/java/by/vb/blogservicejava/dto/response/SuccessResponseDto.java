package by.vb.blogservicejava.dto.response;

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
