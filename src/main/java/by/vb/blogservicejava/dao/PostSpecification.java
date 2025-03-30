package by.vb.blogservicejava.dao;

import by.vb.blogservicejava.dto.Post.PostFilterField;
import by.vb.blogservicejava.dto.Post.PostSortField;
import by.vb.blogservicejava.entity.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public interface PostSpecification {
	/**
	 * Returns specification for filtering conditions
	 *
	 * @param fields filtering conditions
	 * @return specification for filtering conditions
	 */
	Specification<Post> filterConditions(final Map<PostFilterField, String> fields);

	/**
	 * Returns specification for sorting conditions
	 *
	 * @param fields sorting conditions
	 * @return specification for sorting conditions
	 */
	Sort sortConditions(final Map<PostSortField, Boolean> fields);
}
