package by.vb.blogservicejava.dao;

import by.vb.blogservicejava.dto.Post.PostFilterField;
import by.vb.blogservicejava.dto.Post.PostSortField;
import by.vb.blogservicejava.entity.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public interface PostSpecification {
	Specification<Post> filterConditions(final Map<PostFilterField, String> fields);

	Sort sortConditions(final Map<PostSortField, Boolean> fields);
}
