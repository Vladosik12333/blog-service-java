package by.vb.blogservicejava.mapper;

import jakarta.validation.constraints.NotNull;

public interface Mapper<F, T> {
	T mapTo(@NotNull final F fromObject);
}
