package by.vb.blogservicejava.dao;

import by.vb.blogservicejava.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long>,
		JpaSpecificationExecutor<Reaction> {
}
