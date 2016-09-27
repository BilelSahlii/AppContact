package com.idao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface GenericIDao {

	
	public void saveOrUpdate(Object entity);
	public void delete(Object entity);
	public List<Object>findAll(Class clazz);
	public List findByCriteria(Class clazz, Criterion critere,int id);
	public Object findById(Class clazz, Serializable id);
	
}
