package by.vb.blogservicejava.dto;

import by.vb.blogservicejava.entity.RoleType;
import by.vb.blogservicejava.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto implements UserDetails {

	private String username;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserDetailsDto(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities =
				Stream.of(user.getRole().name(), RoleType.USER.name()).map(SimpleGrantedAuthority::new).collect(
						Collectors.toList());
	}
}
