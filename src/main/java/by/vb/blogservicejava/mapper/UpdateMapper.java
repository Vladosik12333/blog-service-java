package by.vb.blogservicejava.mapper;

import jakarta.validation.constraints.NotNull;

public interface UpdateMapper<F, T> {
	/**
	 * Map one object to another based on existence one
	 *
	 * @param fromObject object to map from
	 * @param toObject object to map to
	 * @return mapped object
	 */
	T mapFromTo(@NotNull final F fromObject, @NotNull final T toObject);
}
