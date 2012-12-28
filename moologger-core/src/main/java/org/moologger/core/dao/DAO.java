package org.moologger.core.dao;

import java.util.List;

public interface DAO<T> {
	
	T create(T t);
	
	List<T> getAll();
	
	T get(Long id);
	
	T set(T t);
	
	void delete(T t);

}