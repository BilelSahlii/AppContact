package com.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import com.model.Groupecontact;
import com.model.Membre;
import com.model.Fonction;
import com.model.Physique;
import com.model.Produit;
import com.idao.FonctionDao;
import com.idao.MembreDao;




@ManagedBean(name="fonctionBean")
@SessionScoped
@Scope(value="session")



public class FonctionBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Fonction> list_Fonction;
public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");;
static	public FonctionDao fonctionDao=(FonctionDao) context.getBean("FonctionDao");

 private static Fonction fonction= new Fonction();


 
 



public void initP()
{
	fonction=new Fonction();

	
}

public void ajout()

{	fonctionDao.saveOrUpdate(fonction);
initP();

}



public void suprimer(Membre membre)
{
	fonctionDao.delete(fonction);
}



public List<Fonction> getList_Fonction() {
	List list=fonctionDao.findAll(Fonction.class);
	if (list==null)
	{
	System.out.println("vide");
	
	}
return list;
}

public  Fonction getFonction() {
	return fonction;
}

public  void setFonction(Fonction fonction) {
	FonctionBean.fonction = fonction;
}

public void setList_Fonction(List<Fonction> list_Fonction) {
	this.list_Fonction = list_Fonction;
}











	
}
