package by.vb.blogservicejava.dto.Response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class ErrorResponseDto extends BasicResponseDto {
	private List<String> errors;

	public ErrorResponseDto () {
		super();
		this.message = "Error";
	}
}
