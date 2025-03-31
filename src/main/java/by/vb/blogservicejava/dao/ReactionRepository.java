package by.vb.blogservicejava.dao;

import by.vb.blogservicejava.entity.Reaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long>,
		JpaSpecificationExecutor<Reaction>, ReactionSpecification {
	@EntityGraph(attributePaths = {"user"})
	Page<Reaction> findAll(Specification<Reaction> spec, Pageable pageable);
}
