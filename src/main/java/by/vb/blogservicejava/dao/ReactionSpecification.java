package by.vb.blogservicejava.dao;

import by.vb.blogservicejava.dto.Reaction.ReactionFilterField;
import by.vb.blogservicejava.entity.Reaction;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public interface ReactionSpecification {
	/**
	 * Returns specification for filtering conditions
	 *
	 * @param fields filtering conditions
	 * @return specification for filtering conditions
	 */
	Specification<Reaction> filterConditions(final Map<ReactionFilterField, String> fields);
}
