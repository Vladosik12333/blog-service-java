package by.vb.blogservicejava.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class AuditableEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "is_removed")
	private Boolean isRemoved;

	@PrePersist
	private void prePersist() {
		if (this.createdAt == null) {
			this.createdAt = LocalDateTime.now();
			this.isRemoved = false;
		}
	}

	@PreUpdate
	private void preUpdate() {
		this.modifiedAt = LocalDateTime.now();
	}
}
