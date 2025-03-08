package by.vb.blogservicejava.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateUpdateDto {
	private String username;
	private String firstName;
	private String lastName;
	private String password;
}
