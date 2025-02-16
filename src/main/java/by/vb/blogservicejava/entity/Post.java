package by.vb.blogservicejava.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	private List<Reaction> reactions = new ArrayList<>();
}
