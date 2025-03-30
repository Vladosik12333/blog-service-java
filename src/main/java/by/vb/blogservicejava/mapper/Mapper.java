package by.vb.blogservicejava.mapper;

import jakarta.validation.constraints.NotNull;

public interface Mapper<F, T> {
	/**
	 * Map one object to another
	 *
	 * @param fromObject object to map from
	 * @return mapped object
	 */
	T mapTo(@NotNull final F fromObject);
}
