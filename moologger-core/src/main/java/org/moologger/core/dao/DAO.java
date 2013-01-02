package org.moologger.core.dao;

import java.util.List;
import java.util.Map;

public interface DAO<T> {
	
	T create(T t);
	
	long getCount();
	
	long getCount(Map<String, Object> params);
	
	List<T> getAll();
	
	T get(long id);
	
	T get(Map<String, Object> params);
	
	List<T> getList(Map<String, Object> params);
	
	T set(T t);
	
	void delete(T t);

}