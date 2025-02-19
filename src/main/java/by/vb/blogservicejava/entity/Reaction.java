package by.vb.blogservicejava.entity;

import jakarta.persistence.*;
import lombok.*;

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
		user.addReaction(this);
	}

	public void setPost(Post post) {
		post.addReaction(this);
	}
}
