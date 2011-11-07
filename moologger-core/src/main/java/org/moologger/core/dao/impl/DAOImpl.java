package org.moologger.core.dao.impl;

import org.moologger.core.dao.DAO;
import org.springframework.orm.jpa.support.JpaDaoSupport;

public abstract class DAOImpl<T> extends JpaDaoSupport implements DAO<T> {

    private Class<T> type;

    public DAOImpl(Class<T> type) {
        this.type = type;
    }

    public T create(T t) {
    	getJpaTemplate().persist(t);
        return t;
    }
    
    public T get(Long id) {
        return (T) getJpaTemplate().find(type, id);
    }
    
    public T set(T t) {
        return getJpaTemplate().merge(t);    
    }

    public void delete(T t) {
    	getJpaTemplate().remove(t);
    }

}