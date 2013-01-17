package org.moologger.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.moologger.core.dao.DAO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class DAOImpl<T> implements DAO<T> {
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

    private Class<T> type;

    public DAOImpl(Class<T> type) {
        this.type = type;
    }

    public T create(T t) {
    	getEntityManager().persist(t);
        return t;
    }
    
	public long getCount() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Long> query = builder.createQuery(Long.class);
	    
	    query.select(builder.count(query.from(type)));
	    
    	return getEntityManager().createQuery(query).getSingleResult();
    }
    
    public long getCount(Map<String, Object> params) {
    	CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Long> query = builder.createQuery(Long.class);
	    
	    Root<T> root = query.from(type);
	    query.select(builder.count(root));
	    List<Predicate> predicates = new ArrayList<Predicate>();
	    for (Map.Entry<String, Object> entry : params.entrySet()) {
	    	predicates.add(builder.equal(root.<String>get(entry.getKey()), entry.getValue()));
	    }
	    query.where(predicates.toArray(new Predicate[] {}));
	    
        return getEntityManager().createQuery(query).getSingleResult();
    }
    
	public List<T> getAll() {
    	CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<T> query = builder.createQuery(type);
	    
	    query.select(query.from(type));
	    
    	return getEntityManager().createQuery(query).getResultList();
    }
    
    public T get(long id) {
        return getEntityManager().find(type, id);
    }
    
    public T get(Map<String, Object> params) {
    	CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<T> query = builder.createQuery(type);
	    
	    Root<T> root = query.from(type);
	    query.select(root);
	    List<Predicate> predicates = new ArrayList<Predicate>();
	    for (Map.Entry<String, Object> entry : params.entrySet()) {
	    	predicates.add(builder.equal(root.<String>get(entry.getKey()), entry.getValue()));
	    }
	    query.where(predicates.toArray(new Predicate[] {}));
	    
        return getEntityManager().createQuery(query).getSingleResult();
    }
    
    public List<T> getList(Map<String, Object> params) {
    	CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<T> query = builder.createQuery(type);
	    
	    Root<T> root = query.from(type);
	    query.select(root);
	    List<Predicate> predicates = new ArrayList<Predicate>();
	    for (Map.Entry<String, Object> entry : params.entrySet()) {
	    	predicates.add(builder.equal(root.<String>get(entry.getKey()), entry.getValue()));
	    }
	    query.where(predicates.toArray(new Predicate[] {}));
	    
        return getEntityManager().createQuery(query).getResultList();
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