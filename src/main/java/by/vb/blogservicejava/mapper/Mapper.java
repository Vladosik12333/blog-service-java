package by.vb.blogservicejava.mapper;

public interface Mapper<F, T> {
	T mapTo(F from);
}
