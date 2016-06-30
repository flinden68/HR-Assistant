package ch.belsoft.hrassistant.dao;

import java.util.List;

import nl.elstarit.cloudant.model.ConnectorResponse;

public interface ICrudDAO<T, I> {

	// public void connectToService();

	public void create(T t);

	public T read(I id);

	public List<T> read();

	public void delete(T t);

	public void update(T t);

}
