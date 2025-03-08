package by.vb.blogservicejava.dto.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BasicResponseDto {
	protected Integer code;
	protected String message;
}
