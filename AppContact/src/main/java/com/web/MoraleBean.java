package com.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import  org.primefaces.*;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;






import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.Visibility;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.w3c.dom.events.EventException;

































import com.model.Activite;
import com.model.Groupecontact;
import com.model.Membre;
import com.model.Morale;
import com.model.Secteur;
import com.model.Region;
import com.model.Typemoral;
import com.model.Chefresponsable;
import com.util.MailMail;
import com.idao.*;




@ManagedBean(name="moraleBean")
@SessionScoped
@Scope(value="session")



public class MoraleBean implements Serializable {
private static final long serialVersionUID = -954873828500119175L;
private	List <Morale> list_Morale;
private	List <Morale> selected_list_Morale;





public List<Morale> getSelected_list_Morale() {
	return selected_list_Morale;
}






public void setSelected_list_Morale(List<Morale> selected_list_Morale) {
	this.selected_list_Morale = selected_list_Morale;
}


private	List <Membre> list_Membre_Morale;
private static ApplicationContext contextEmail = new ClassPathXmlApplicationContext("Spring-Mail.xml"); 
public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");
static	public MoraleDao moraleDao=(MoraleDao) context.getBean("MoraleDao");

static	public MembreDao membreDao=(MembreDao) context.getBean("MembreDao");
static public  MailMail mm = (MailMail) contextEmail.getBean("mailMail"); 
 private static Membre membre= new Membre();

static private Morale morale=new Morale();





 private List<Boolean> list;
 static private UploadedFile file ;

 
 
 










public String getDestinateur() {
	return destinateur;
}






public void setDestinateur(String destinateur) {
	this.destinateur = destinateur;
}


private String destinateur;
private String objet ;
private String msg ;










public UploadedFile getFile() {
    return file;
}

public void setFile(UploadedFile file) {
    this.file = file;
}


public String getObjet() {
	return objet;
}

public void setObjet(String objet) {
	this.objet = objet;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}



void initMailinig()
{
	selected_list_Morale=null;
	morale=null ;
			msg="";
	destinateur="";
	objet="";
}



public void EnvoiEmail()
{
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean envoyer = false;
	


MailMail mm = (MailMail) contextEmail.getBean("mailMail");




try{ 
	if(	morale==null)
	{
		envoyer = false;
		message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Il faut selectionner un contact", "");
	}
	
	else
	{
	mm.sendMail("bilell.sahli@gmail.com",
morale.getEmail(),
	  objet,
	msg);
message = new FacesMessage(FacesMessage.SEVERITY_INFO, "est bien envoyé à", morale.getNom());
envoyer = true;

		}
}
		catch(Exception e)
		{
	envoyer = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email erroné", "");
			initMailinig();
		}



FacesContext.getCurrentInstance().addMessage(null, message);
context.addCallbackParam("envoyer", envoyer);
context.update("formMorale:tab");
initMailinig();


}






public void EnvoiGroupe()
{
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean envoyer = false;
	List<Morale> list= selected_list_Morale;
	Iterator<Morale> iter = list.iterator();
	if (!iter.hasNext()) { 

		return;
	}
		else
	
		{
			while (iter.hasNext()) {
		
				
		
			try{	Morale ctmorale = (Morale) iter.next();
			

			System.out.println("id morale "+ ctmorale.getIdMorale());
		EnvoiEmailGroupe(ctmorale) ;
		System.out.println("id morale "+ ctmorale.getIdMorale());
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "est bien envoyé à", ctmorale.getNom());
	envoyer = true;
	initMailinig();
	
			}
			catch(Exception e)
			{
		envoyer = false;
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email erroné", "");
				initMailinig();
			}

			FacesContext.getCurrentInstance().addMessage(null, message);
			context.addCallbackParam("envoyer", envoyer);
			context.update("formMorale:tab");
			initMailinig();
				
			}
		
		}
		}
	



public void EnvoiEmailGroupe(Morale ctmorale)
{
	


MailMail mm = (MailMail) contextEmail.getBean("mailMail");
mm.sendMail("bilell.sahli@gmail.com",
	ctmorale.getEmail(),
	 objet,
msg);

initMailinig();

}











 


 private static Membre selectmembre= new Membre();
 
 public void affiche(Membre smembre)
 {
	 System.out.println("ID Membre "+smembre.getIdmembre());
 }
 
 public Membre getSelectmembre() {
	return selectmembre;
}


public  void setSelectmembre(Membre selectmembre) {
	MoraleBean.selectmembre = selectmembre;
}


public  Morale getMorale() {
	return morale;
}


public  void setMorale(Morale morale) {
	MoraleBean.morale = morale;
}







public List<Boolean> getList() {
	return list;
}


public void setList(List<Boolean> list) {
	this.list = list;
}


public void onToggle(ToggleEvent e) {
     list.set((Integer) e.getData(), e.getVisibility() == Visibility.VISIBLE);
 }




public void AjouterMembreContact() throws IOException {

	RequestContext context = RequestContext.getCurrentInstance();
	context.update("AjouterMorale");
	context.execute("PF('addmorale').show();");

	
}


public void AjouterMembreContactsansInit() throws IOException {

	RequestContext context = RequestContext.getCurrentInstance();
	context.update("AjouterMorale");
//	context.execute("PF('addmorale').show();");
	context.execute("PF('UploadImage').hide();");

}





public void AjouterImage() throws IOException {

	RequestContext context = RequestContext.getCurrentInstance();
	context.update("ajoutImage");
	context.execute("PF('UploadImage').show();");

	
}



public void EmmailingSelectMorale() throws IOException {

	  if(morale!=null)
	     {
	RequestContext context = RequestContext.getCurrentInstance();
	context.update("SelectedmailContactMorale");
	context.execute("PF('ViewEmail').show();");
//	selectMorale=null;
	     }
    else
    {
	FacesMessage message = null;


	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "il faut sélectionner un ligne ", "");
	FacesContext.getCurrentInstance().addMessage(null, message);
	RequestContext context = RequestContext.getCurrentInstance();
	context.update("formMorale:tab");
    }
	
}



public void EmmailingGroupe() throws IOException {

	RequestContext context = RequestContext.getCurrentInstance();
	context.update("edmailGroupe");
	context.execute("PF('ViewEmailG').show();");
}


public void MembreContact() throws IOException {
     if(morale!=null)
     {
	RequestContext context = RequestContext.getCurrentInstance();
	context.update("MembreMorale");
	context.execute("PF('Viewmorale').show();");
//	selectMorale=null;
     }
     
     else
     {
 	FacesMessage message = null;


	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "il faut sélectionner un ligne ", "");
	FacesContext.getCurrentInstance().addMessage(null, message);
	RequestContext context = RequestContext.getCurrentInstance();
	context.update("formMorale:tab");
     }
     
     
     
     
     
     
 	
}




public void handleFileUpload(FileUploadEvent event) throws IOException {
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean add = false;
	
	if(event.getFile()!= null) {
    	try {
    		
    		file=event.getFile();
    		
   
	 message = new FacesMessage("la photo ", file.getFileName() + "est enregistrée ");


} 
catch (Exception e)

{

add = false;
	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout d'mage erroné", "");

}

FacesContext.getCurrentInstance().addMessage(null, message);
context.addCallbackParam("add",add);
context.update("AjouterMorale:panelAdd");

}

	        
	
	

}
		
		void insererImage(Morale contactImg) throws IOException
		{

			File file2=new File("c:\\Users\\bilel\\git\\localToolsRepository\\AppContact\\src\\main\\webapp\\images\\contact\\"+contactImg.getIdMorale()+".jpg");


			try {

			InputStream inputstream= file.getInputstream() ;
			moraleDao.saveFile(inputstream, file2);
			if((morale.getTelephone()==null) ||(morale.getTelephone()==0))
			{
				morale.setTelephone(null);
			}
			
			
			if((morale.getMobile()==null) ||(morale.getMobile()==0))
			{
				morale.setMobile(null);
			}
			
			
			if((morale.getFax()==null) ||(morale.getFax()==0))
			{
				morale.setFax(null);
			}
			
			
			if((morale.getCodePostale()==null) ||(morale.getCodePostale()==0))
			{
				morale.setCodePostale(null);
			}
			
			
			
			}
			catch (Exception e)

			{



			}
			
		}

		
		
		
		public String  ajout() throws IOException

		{	
			RequestContext context = RequestContext.getCurrentInstance();
			FacesMessage message = null;
			boolean add = false;	


			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			morale.setDate(sqlDate);
		try {moraleDao.saveOrUpdate(morale);
		insererImage(morale);


		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "le contact "+morale.getNom()+" est bien enregistré", "");
		add = true;
		

		init();
		return "successAjout" ;
		




		} 
		catch (Exception e)

		{

		add = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout erroné", "");
			init();
		}






		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("add",add);
		context.update("formMorale:tab");


		init();
		return "successAjout" ;
		}
	 

		
		public void init()
		{

		morale=new Morale();
	    file=null;

		}
		


public String  Modifier() throws IOException

{	
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean add = false;	


	
try {moraleDao.saveOrUpdate(morale);
insererImage(morale);


message = new FacesMessage(FacesMessage.SEVERITY_INFO, "le contact "+morale.getNom()+" est bien modifié", "");
add = true;


return "successModifier" ;





} 
catch (Exception e)

{

add = false;
	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout erroné", "");

}






FacesContext.getCurrentInstance().addMessage(null, message);
context.addCallbackParam("add",add);
context.update("formMorale:tab");



return "successModifier" ;
}















public void suprimer(Morale morale)
{
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean deleted = false;
	
try{
	
if(morale==null)
{
	deleted = false;

	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "il faut sélectionner un ligne ", "");

}
else
{
moraleDao.delete(morale);

	message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Supprimé", morale.getNom());
	deleted = true;
}
	
}catch (Exception e)

{
	deleted = false;
	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Supression erroné", "");
}

FacesContext.getCurrentInstance().addMessage(null, message);
context.addCallbackParam("deleted", deleted);
context.update("formMorale:tab");
morale=null;


}



public List<Morale> getList_Morale() {
  List list=moraleDao.findAll(Morale.class);
	return list;
}



public void setList_Morale(List<Morale> list_Morale) {
	this.list_Morale = list_Morale;
}







public  void afficheSelectMorale(List selectLMoral) {
	List<Morale> list= selectLMoral;
	Iterator<Morale> iter = list.iterator();
	if (!iter.hasNext()) { 

		return;
	}
		else
	
		{
			while (iter.hasNext()) {
			
				
		Morale ctmorale = (Morale) iter.next();

			}
		}
}





public static Membre getMembre() {
	return membre;
}


public static void setMembre(Membre membre) {
	MoraleBean.membre = membre;
}


public void setList_Membre_Morale(List<Membre> list_Membre_Morale) {
	this.list_Membre_Morale = list_Membre_Morale;
}


public List<Membre> getList_Membre_Morale() {
	List list=null;
	Criterion critere1;

	critere1 = Restrictions.eq("idContact",37);
	
	
	try{
		if(morale.getIdMorale()!=0)
		{

	
 list= membreDao.findByCriteria(Membre.class, critere1,morale.getIdMorale()) ;
	
	}
	morale=null;
		return list;
	}
	
	catch(Exception e)
	{
		
	}
	return list;
	
	
	
	
	
	
}









public String modifierMorale()
{
	
	
	if(morale!=null)
		return "successModifier" ;
	else
		return "" ;
}




public String modifierM()
{
	
	if(morale!=null)
	{
		
	
		moraleDao.saveOrUpdate(morale);
	
		return "Accuile" ;
	}
	else
		return "" ;
}











	
}
