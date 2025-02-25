package by.vb.blogservicejava.dao.Impl;

import by.vb.blogservicejava.dao.PostSpecification;
import by.vb.blogservicejava.dto.PostFilter;
import by.vb.blogservicejava.dto.PostSort;
import by.vb.blogservicejava.entity.Post;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PostSpecificationImpl implements PostSpecification {
	public Specification<Post> filterConditions(final PostFilter.FilterBy filterBy) {
		return (book, cq, cb) -> {
			final List<Predicate> filters = new ArrayList<>();

			if (filterBy.getTitle() != null && !filterBy.getTitle().isBlank())
				filters.add(cb.like(book.get("title"), "%" + filterBy.getTitle() + "%"));

			return cb.and(filters.toArray((new Predicate[0])));
		};
	}

	@Override
	public Sort sortConditions(final PostSort.SortBy sortBy) {
		List<Sort.Order> orders = new ArrayList<>();

		if (sortBy.getTitle() != null)
			orders.add(new Sort.Order(sortDirection(sortBy.getTitle()), "title"));

		if (sortBy.getDescription() != null)
			orders.add(new Sort.Order(sortDirection(sortBy.getDescription()), "description"));

		if (sortBy.getUsername() != null)
			orders.add(new Sort.Order(sortDirection(sortBy.getUsername()), "user.username"));

		if (sortBy.getFirstName() != null)
			orders.add(new Sort.Order(sortDirection(sortBy.getFirstName()), "user.firstName"));

		if (sortBy.getLastName() != null)
			orders.add(new Sort.Order(sortDirection(sortBy.getLastName()), "user.lastName"));

		if (sortBy.getCreatedAt() != null)
			orders.add(new Sort.Order(sortDirection(sortBy.getCreatedAt()), "createdAt"));

		return orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);
	}

	private Sort.Direction sortDirection(boolean ascending) {
		return ascending ? Sort.Direction.ASC : Sort.Direction.DESC;
	}
}
