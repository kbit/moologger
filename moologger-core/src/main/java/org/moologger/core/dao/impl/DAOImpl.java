package org.moologger.core.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.moologger.core.dao.DAO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class DAOImpl<T> implements DAO<T> {
	
	@PersistenceContext
	private EntityManager entityManager;

    private Class<T> type;

    public DAOImpl(Class<T> type) {
        this.type = type;
    }

    public T create(T t) {
    	getEntityManager().persist(t);
        return t;
    }
    
    @SuppressWarnings("unchecked")
	public List<T> getAll() {
    	return getEntityManager().createQuery("FROM " + type.getSimpleName()).getResultList();
    }
    
    public T get(Long id) {
        return (T) getEntityManager().find(type, id);
    }
    
    public T set(T t) {
        return getEntityManager().merge(t);    
    }

    public void delete(T t) {
    	getEntityManager().remove(t);
    }

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}