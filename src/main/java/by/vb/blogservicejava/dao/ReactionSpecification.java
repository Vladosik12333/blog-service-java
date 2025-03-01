package by.vb.blogservicejava.dao;

import by.vb.blogservicejava.dto.ReactionFilterField;
import by.vb.blogservicejava.entity.Reaction;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public interface ReactionSpecification {
	Specification<Reaction> filterConditions(final Map<ReactionFilterField, String> fields);
}
