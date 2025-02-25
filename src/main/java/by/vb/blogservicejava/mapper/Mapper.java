package by.vb.blogservicejava.mapper;


import by.vb.blogservicejava.exception.NotFoundResourceException;
import jakarta.validation.constraints.NotNull;

public interface Mapper<F, T> {
	T mapTo(@NotNull final F fromObject) throws NotFoundResourceException;
}
