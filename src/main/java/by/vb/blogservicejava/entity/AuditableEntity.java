package by.vb.blogservicejava.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
	protected Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	protected LocalDateTime createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_at", nullable = false)
	protected LocalDateTime modifiedAt;

	@PrePersist
	private void prePersist() {
		LocalDateTime currentDate = LocalDateTime.now();

		if (Objects.isNull(this.createdAt))
			this.createdAt = currentDate;
		if (Objects.isNull(this.modifiedAt))
			this.modifiedAt = currentDate;
	}

	@PreUpdate
	private void preUpdate() {
		this.modifiedAt = LocalDateTime.now();
	}
}
