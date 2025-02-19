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
}
