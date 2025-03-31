package by.vb.blogservicejava.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"reactions"})
public class Post extends AuditableEntity {
	@Column(nullable = false)
	private String title;

	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Builder.Default
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@BatchSize(size = 100)
	private List<Reaction> reactions = new ArrayList<>();

	public void addReaction(Reaction reaction) {
		reaction.setPost(this);
		this.reactions.add(reaction);
	}

	public void setUser(User user) {
		this.user = user;
		user.getPosts().add(this);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Post post))
			return false;
		return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(
				description,
				post.description) && Objects.equals(user, post.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, description, user);
	}
}
