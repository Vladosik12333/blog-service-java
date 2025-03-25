package by.vb.blogservicejava.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"posts", "reactions"})
public class User extends AuditableEntity implements UserDetails {
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoleType role;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Builder.Default
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reaction> reactions = new ArrayList<>();

	public void addPost(Post post) {
		post.setUser(this);
		this.posts.add(post);
	}

	public void addReaction(Reaction reaction) {
		reaction.setUser(this);
		this.reactions.add(reaction);
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return Stream.of("ROLE_" + this.getRole().name())
				.map(SimpleGrantedAuthority::new)
				.collect(
						Collectors.toList());
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof User user))
			return false;
		return Objects.equals(id, user.id) && Objects.equals(username,
				user.username) && Objects.equals(password,
				user.password) && Objects.equals(firstName,
				user.firstName) && Objects.equals(lastName, user.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, firstName, lastName);
	}
}
