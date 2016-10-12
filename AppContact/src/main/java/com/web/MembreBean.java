package com.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import  org.primefaces.*;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;






import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.w3c.dom.events.EventException;

import com.model.Chefresponsable;
import com.model.Fonction;
import com.model.Groupecontact;
import com.model.Membre;
import com.model.Morale;
import com.model.Physique;
import com.idao.MembreDao;
import com.idao.FonctionDao;
import com.idao.MoraleDao;




@ManagedBean(name="membreBean")
@SessionScoped
@Scope(value="session")



public class MembreBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Membre> list_Membre;
private	List <Membre> list_Membre_Morale;

public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");;
static	public MembreDao membreDao=(MembreDao) context.getBean("MembreDao");
static public MoraleDao moraleDao = (MoraleDao) context
.getBean("MoraleDao");


static private UploadedFile file;

 static  private  Membre membre= new Membre();
 


private static Membre selectmembre= new Membre();
 
 
 
public UploadedFile getFile() {
	return file;
}

public void setFile(UploadedFile file) {
	this.file = file;
}


 
 



public  Membre getSelectmembre() {
	try{ if(selectmembre.getIdmembre()!=0)
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

	membre=null;
	file=null;

	
}



public void AjouterMembreContactsansInit() throws IOException {

	RequestContext context = RequestContext.getCurrentInstance();
	context.update("AjouterMoraleMembre");
//	context.execute("PF('addmorale').show();");
	context.execute("PF('Viewmorale').show();");

}



public void AjouterMembreContact() throws IOException {

	RequestContext context = RequestContext.getCurrentInstance();
	context.update("AjouterMoraleMembre");
	context.execute("PF('addmoraleMembre').show();");

	
}



public void handleFileUploadMembre(FileUploadEvent event) throws IOException {
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean add = false;

	if (event.getFile() != null) {
		try {

			file = event.getFile();

			message = new FacesMessage("la photo ", file.getFileName()
					+ "est enregistrée ");

		} catch (Exception e)

		{

			add = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ajout d'mage erroné", "");

		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("add", add);
		context.update("AjouterMorale:panelAdd");

	}

}

void insererImageMembre(Membre contactImg) throws IOException {

	File file2 = new File(
			"c:\\Users\\bilel\\git\\localToolsRepository\\AppContact\\src\\main\\webapp\\images\\contact\\membre\\"
					+ contactImg.getIdmembre() + ".jpg");

	try {

		InputStream inputstream = file.getInputstream();
	membreDao.saveFile(inputstream, file2);


	} catch (Exception e)

	{

	}

}	        
	
	






public void ajoutMembre(int id)

{RequestContext context = RequestContext.getCurrentInstance();
FacesMessage message = null;
boolean add = false;

	
	try{
		
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		membre.setDate(sqlDate);
	Morale	morale= (Morale) moraleDao.findById(Morale.class, id);
			
		membre.setMorale(morale);

		membreDao.saveOrUpdate(membre);
		
		insererImageMembre(membre);
	message = new FacesMessage(FacesMessage.SEVERITY_INFO, "le membre "+membre.getNom()+" est bien enregistré", "");
	add = true;
	}
	catch (Exception e)

	{

	add = false;
		message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout erroné", "");
		initP();
	}






	FacesContext.getCurrentInstance().addMessage(null, message);
	context.addCallbackParam("add",add);
	context.update("MembreMorale:tabMembre");



//initP();

}



public void suprimer(Membre membre)
{
	membreDao.delete(membre);
	
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean deleted = false;

	try {


			membreDao.delete(membre);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Supprimé", membre.getNom());
			deleted = true;


	} catch (Exception e)

	{
		deleted = false;
		message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Supression erroné", "");
	}

	FacesContext.getCurrentInstance().addMessage(null, message);
	context.addCallbackParam("deleted", deleted);
	context.update("MembreMorale:tabMembre");


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

	critere1 = Restrictions.eq("idContact",37);

	List list= membreDao.findByCriteria(Membre.class, critere1,37) ;
	
	System.out.print("size = " +list.size());
	
	
	
	
	return list;
}

public void setList_Membre_Morale(List<Membre> list_Membre_Morale) {
	this.list_Membre_Morale = list_Membre_Morale;
}













	
}
