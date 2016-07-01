package ch.belsoft.hrassistant.dao;

import java.util.List;

public interface ICrudDAO<T, I> {
    
    // public void connectToService();
    
    public void create(T t);
    
    public T read(I id);
    
    public List<T> read();
    
    public List<T> readWithKeys(String startKey, String endKey);
    
    public void delete(T t);
    
    public void update(T t);
    
}
