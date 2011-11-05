package org.moologger.core.dao;

public interface DAO<T> {
	
	T create(T t);
	
	T get(Long id);
	
	T set(T t);
	
	void delete(T t);

}