package by.vb.blogservicejava.dao.Impl;

import by.vb.blogservicejava.dao.PostSpecification;
import by.vb.blogservicejava.dto.PostFilterField;
import by.vb.blogservicejava.dto.PostSortField;
import by.vb.blogservicejava.entity.Post;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostSpecificationImpl implements PostSpecification {
	public Specification<Post> filterConditions(final Map<PostFilterField, String> fields) {
		return (post, cq, cb) -> {
			final List<Predicate> filters = new ArrayList<>();

			fields.forEach((key, value) -> {

				if (value == null || value.isBlank())
					return;

				filters.add(cb.like(post.get(key.getColumnName()), "%" + value + "%"));
			});

			return cb.and(filters.toArray(new Predicate[0]));
		};
	}

	@Override
	public Sort sortConditions(final Map<PostSortField, Boolean> fields) {
		List<Sort.Order> orders = new ArrayList<>();

		fields.forEach((key, value) -> {

			if (value == null)
				return;

			orders.add(new Sort.Order(sortDirection(value), key.getColumnName()));
		});

		return orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);
	}

	private Sort.Direction sortDirection(boolean ascending) {
		return ascending ? Sort.Direction.ASC : Sort.Direction.DESC;
	}
}
