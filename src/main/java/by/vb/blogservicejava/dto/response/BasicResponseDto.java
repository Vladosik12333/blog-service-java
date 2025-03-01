package by.vb.blogservicejava.dto.response;

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
