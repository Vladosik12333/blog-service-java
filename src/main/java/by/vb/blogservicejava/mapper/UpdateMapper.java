package by.vb.blogservicejava.mapper;

import jakarta.validation.constraints.NotNull;

public interface UpdateMapper<F, T> {
	T mapFromTo(@NotNull final F fromObject, @NotNull final T toObject);
}
