package com.hit.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao <V, T> {
	
	T get(V id);
	
	T getByValue(V value) throws Exception;
	
	List<T> getAll();
		
	void save(T t);
	
	void delete(V id);
		
}
