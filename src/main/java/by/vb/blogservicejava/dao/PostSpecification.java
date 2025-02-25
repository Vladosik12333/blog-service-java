package by.vb.blogservicejava.dao;

import by.vb.blogservicejava.dto.PostFilter;
import by.vb.blogservicejava.dto.PostSort;
import by.vb.blogservicejava.entity.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface PostSpecification {
	Specification<Post> filterConditions(final PostFilter.FilterBy filterBy);

	Sort sortConditions(final PostSort.SortBy sortBy);
}
