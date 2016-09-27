package com.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import  org.primefaces.*;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;






import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.w3c.dom.events.EventException;

import com.model.Contact;
import com.model.Groupecontact;
import com.model.Membre;
import com.model.Morale;
import com.model.Physique;
import com.model.Produit;
import com.idao.MembreDao;




@ManagedBean(name="membreBean")
@SessionScoped
@Scope(value="session")



public class MembreBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Membre> list_Membre;
private	List <Membre> list_Membre_Morale;

public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");;
static	public MembreDao membreDao=(MembreDao) context.getBean("MembreDao");

 private static Membre membre= new Membre();
 private static Membre selectmembre= new Membre();
 
 
 


 
 



public  Membre getSelectmembre() {
	try{ if(selectmembre.getIdmembre()!=null)
		return selectmembre;
		
	}catch(Exception e)
	{
		System.out.println("vide");
	}
	return selectmembre;
}

public  void setSelectmembre(Membre selectmembre) {
	MembreBean.selectmembre = selectmembre;
}

public void initP()
{
	membre=new Membre();

	
}

public void ajout()

{	membreDao.saveOrUpdate(membre);
initP();

}



public void suprimer(Membre membre)
{
	membreDao.delete(membre);
}



public List<Membre> getList_Membre() {
	List list=membreDao.findAll(Membre.class);
return list;
}

public  Membre getMembre() {
	return membre;
}

public  void setMembre(Membre membre) {
	MembreBean.membre = membre;
}

public void setList_Membre(List<Membre> list_Membre) {
	this.list_Membre = list_Membre;
}

public List<Membre> getList_Membre_Morale() {
	
	Criterion critere1;
//	critere1 = Restrictions.eq("idContact",membre.getMorale().getContact().getIdContact());
//	morale.setGroupecontact((Groupecontact) groupecontactDao.findById(Groupecontact.class, groupe.getIdGroupe())); 
	critere1 = Restrictions.eq("idContact",37);

	List list= membreDao.findByCriteria(Membre.class, critere1,37) ;
	
	System.out.print("size = " +list.size());
	
	
	
	
	return list;
}

public void setList_Membre_Morale(List<Membre> list_Membre_Morale) {
	this.list_Membre_Morale = list_Membre_Morale;
}













	
}
