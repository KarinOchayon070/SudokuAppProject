package com.hit.dao;


public interface Dao <V, T> {
	
	T get(V value);
	
	void save(T t);
}
