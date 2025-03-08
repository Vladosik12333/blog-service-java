package by.vb.blogservicejava.dao.Impl;

import by.vb.blogservicejava.dao.ReactionSpecification;
import by.vb.blogservicejava.dto.Reaction.ReactionFilterField;
import by.vb.blogservicejava.entity.Reaction;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReactionSpecificationImpl implements ReactionSpecification {
	@Override
	public Specification<Reaction> filterConditions(Map<ReactionFilterField, String> fields) {
		return (reaction, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			fields.forEach((field, value) ->
					predicates.add(cb.equal(reaction.get(field.getColumnName()),
							value.toUpperCase())));

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
