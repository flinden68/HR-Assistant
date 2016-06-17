package ch.belsoft.hrassistant.dao;

import java.util.List;

public interface ICrudDAO<T, I> {

	public T create(T t);

	public T read(I id);

	public List<T> read();

	public void delete(T t);

	public T update(T t);

}
