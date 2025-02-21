package by.vb.blogservicejava.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "reactions")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reaction extends AuditableEntity {
	@Enumerated(EnumType.STRING)
	private ReactionType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	public void setUser(User user) {
		this.user = user;
		user.getReactions().add(this);
	}

	public void setPost(Post post) {
		this.post = post;
		post.getReactions().add(this);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Reaction reaction))
			return false;
		return Objects.equals(id, reaction.id) && type == reaction.type && Objects.equals(user,
				reaction.user) && Objects.equals(post,
				reaction.post);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, type, user, post);
	}
}
