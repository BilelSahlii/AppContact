package com.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import  org.primefaces.*;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
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
import com.model.Morale;
import com.model.Physique;

import com.model.Region;
import com.model.Secteur;
import com.idao.MoraleDao;
import com.idao.GroupeContactDao;
import com.idao.PhysiqueDao;

import com.idao.RegionDao;
import com.idao.SecteurDao;



@ManagedBean(name="secteurBean")
@SessionScoped
@Scope(value="session")



public class SecteurBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Secteur> list_Secteur;
private	List <Secteur> selectlist_Secteur;

public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");;
static	public SecteurDao secteurDao=(SecteurDao) context.getBean("SecteurDao");

static private  Secteur secteur=new Secteur();
static private  Secteur secteurItem=new Secteur();



 
public static Secteur getSecteurItem() {
	return secteurItem;
}


public static void setSecteurItem(Secteur secteurItem) {
	SecteurBean.secteurItem = secteurItem;
}


public List<Secteur> getSelectlist_Secteur() {
	return selectlist_Secteur;
}


public void setSelectlist_Secteur(List<Secteur> selectlist_Secteur) {
	this.selectlist_Secteur = selectlist_Secteur;
}


public Secteur getSecteur() {
	return secteur;
}


public  void setSecteur(Secteur secteur) {
SecteurBean.secteur = secteur;
}


public void AjouterSecteur() throws IOException {

	RequestContext context = RequestContext.getCurrentInstance();
	context.update("ajouterSecteur");
	context.execute("PF('addSecteur').show();");
}



public void AjouterMembreContact() throws IOException {

	RequestContext context = RequestContext.getCurrentInstance();
	context.update("AjouterMorale");
	context.execute("PF('addmorale').show();");

	
}


public void init_secteur()
{secteur=new Secteur() ;

}

public void ajout()

{
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean adds = false;
	
	
	try {secteurDao.saveOrUpdate(secteur);


	message = new FacesMessage(FacesMessage.SEVERITY_INFO, "le Secteur "+secteur.getLibelleSecteur()+" est bien enregistré", "");
	adds = true;

	init_secteur();


	} 
	catch (Exception e)

	{

	adds = false;
		message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout erroné", "");
		init_secteur();
		
	}



	FacesContext.getCurrentInstance().addMessage(null, message);
	context.addCallbackParam("adds",adds);
	context.update("AjouterMorale:panelAdd");

	init_secteur();
	

	
	}




public void suprimer()
{RequestContext context = RequestContext.getCurrentInstance();
FacesMessage message = null;
boolean deleted = false;
	try {
		
		if(secteur==null)
		{
			deleted = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sellectionner un secteur", "");
		}
		else
		{
		secteurDao.delete(secteur);
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Supprimé", secteur.getLibelleSecteur());
		deleted = true;
		}
	
	
	


	
}catch (Exception e)

{
	deleted = false;
	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Supression erroné", "");
}

FacesContext.getCurrentInstance().addMessage(null, message);
context.addCallbackParam("deleted", deleted);
context.update("AjouterMorale:addmorale");
init_secteur();
}






public List<Secteur> getList_Secteur() {
	List list=secteurDao.findAll(Secteur.class);
	return list;

}



public void setList_Secteur(List<Secteur> list_Secteur) {
	this.list_Secteur = list_Secteur;
}


public void EnvoiGroupe()
{	

	List<Secteur> list= selectlist_Secteur;
	Iterator<Secteur> iter = list.iterator();
	if (!iter.hasNext()) { 
		System.out.println("la liste est vide");
		return;
	}
		else
	
		{
			while (iter.hasNext()) {
				System.out.println("la liste non vide");
				
	Secteur ct = (Secteur) iter.next();
		System.out.println("value "+ct.getLibelleSecteur());
		
//	EnvoiEmailGroupe(ct) ;
		
		}
		}

}




	
}
