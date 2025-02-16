package by.vb.blogservicejava.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"posts"})
public class User extends AuditableEntity {
	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Builder.Default
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
}
