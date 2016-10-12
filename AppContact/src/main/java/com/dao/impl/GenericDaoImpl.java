package com.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.idao.GenericIDao;
import com.model.Membre;
import com.model.Morale;

@Service("genericDao")
@Transactional
public class GenericDaoImpl implements GenericIDao {
	
//	@Autowired
//	HibernateTemplate template;
	
	@Autowired
	protected SessionFactory sessionFactory;

public void saveOrUpdate(Object entity) {
	// TODO Auto-generated method stub
	sessionFactory.getCurrentSession().saveOrUpdate(entity);
	
}

public void delete(Object entity) {
	// TODO Auto-generated method stub
	sessionFactory.getCurrentSession().delete(entity);
}

public List<Object> findAll(Class clazz) {
	// TODO Auto-generated method stub
	List<Object> list = new ArrayList<Object>();
	Query query =  sessionFactory.getCurrentSession().createQuery("from "+clazz.getName());
	list = query.list();
	return list;
}

public List <Membre> findByCriteria(Class clazz, Criterion critere,int id) {
	// TODO Auto-generated method stub
	List objects = null;
	

	  Query query = sessionFactory.getCurrentSession().createQuery(" FROM Membre where idMorale="+ id );
	

	  List elist = query.list();
	  
return elist;
}




public List findByCriteriaMembre() {
	// TODO Auto-generated method stub
	List objects = null;
	
	
	 objects=  (List) sessionFactory.getCurrentSession().createCriteria("SELECT * FROM `membre` WHERE idContact=37");
//	Criteria crit= sessionFactory.getCurrentSession().createCriteria(Morale.class).setFetchMode("Membre",FetchMode.JOIN).add(Restrictions.eq("idContact",37));
//	objects=crit.list();
return objects;
}



public Object findById(Class clazz, Serializable id) {
	// TODO Auto-generated method stub
	Object e = sessionFactory.getCurrentSession().load(clazz, id);
	return e;
}



}
