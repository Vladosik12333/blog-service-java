package by.vb.blogservicejava.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

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
	@Column(name = "modified_at", nullable = false)
	private LocalDateTime modifiedAt;

	@Column(name = "is_removed", nullable = false)
	private Boolean isRemoved;

	@PrePersist
	private void prePersist() {
		LocalDateTime currentDate = LocalDateTime.now();

		if (Objects.isNull(this.createdAt))
			this.createdAt = currentDate;
		if (Objects.isNull(this.modifiedAt))
			this.modifiedAt = currentDate;
		if (Objects.isNull(this.isRemoved))
			this.isRemoved = false;
	}

	@PreUpdate
	private void preUpdate() {
		this.modifiedAt = LocalDateTime.now();
	}
}
