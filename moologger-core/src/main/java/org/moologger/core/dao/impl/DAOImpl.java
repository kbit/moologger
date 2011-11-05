package org.moologger.core.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.moologger.core.dao.DAO;

public abstract class DAOImpl<T> implements DAO<T> {
	
	@PersistenceContext
    private EntityManager entityManager;

    private Class<T> type;

    public DAOImpl(Class<T> type) {
        this.type = type;
    }

    public T create(T t) {
        entityManager.persist(t);
        return t;
    }
    
    public T get(Long id) {
        return (T) entityManager.find(type, id);
    }
    
    public T set(T t) {
        return entityManager.merge(t);    
    }

    public void delete(T t) {
        entityManager.remove(t);
    }
    
    public EntityManager getEntityManager() {
    	return entityManager;
    }
    
    public void setEntityManager(EntityManager entityManager) {
    	this.entityManager = entityManager;
    }

}