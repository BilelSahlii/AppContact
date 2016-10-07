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





























import com.model.Contact;
import com.model.Groupecontact;
import com.model.Membre;
import com.model.Morale;
import com.model.Secteur;
import com.model.Produit;
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
static	public ChefResponsableDao chefResponsableDao=(ChefResponsableDao) context.getBean("ChefResponsableDao");
static	public ContactDao contactDao=(ContactDao) context.getBean("ContactDao");
static	public GroupeContactDao groupecontactDao=(GroupeContactDao) context.getBean("GroupeContactDao");
static	public ProduitDao produitDao=(ProduitDao) context.getBean("ProduitDao");
static	public RegionDao regionDao=(RegionDao) context.getBean("RegionDao");
static	public SecteurDao secteurDao=(SecteurDao) context.getBean("SecteurDao");
static	public TypeMoralDao typeMoralDao=(TypeMoralDao) context.getBean("TypeMoralDao");
static	public MembreDao membreDao=(MembreDao) context.getBean("MembreDao");
static public  MailMail mm = (MailMail) contextEmail.getBean("mailMail"); 
 private static Membre membre= new Membre();

static private Morale morale=new Morale();
 static private Morale selectMorale=new Morale();



private static Secteur secteur=new Secteur();
 private static Region region=new Region();
 private static Groupecontact groupe=new Groupecontact();
 private static Typemoral type=new Typemoral();
 private static Contact contact=new Contact();
 private static Chefresponsable chef=new Chefresponsable();
 private static Produit produit=new Produit();
 private List<Boolean> list;
 static Contact lastContact ;
 
 
 
 
public static Contact getLastContact() {
	Contact ctmorale = null;
	List<Object> list=moraleDao.findAll(Contact.class);
	Iterator<Object> iter = list.iterator();
	if (!iter.hasNext()) { 

		return (Contact) iter;
	}
		else
	
		{
			while (iter.hasNext()) {
			
				
 ctmorale = (Contact) iter.next();

			}
		}

	
	return ctmorale;
}






public static void setLastContact(Contact lastContact) {
	MoraleBean.lastContact = lastContact;
}


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
	selectMorale=null ;
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
	if(	selectMorale==null)
	{
		envoyer = false;
		message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Il faut selectionner un contact", "");
	}
	
	else
	{
	mm.sendMail("bilell.sahli@gmail.com",
	selectMorale.getContact().getEmail(),
	  objet,
	msg);
message = new FacesMessage(FacesMessage.SEVERITY_INFO, "est bien envoy� �", selectMorale.getContact().getNom());
envoyer = true;

		}
}
		catch(Exception e)
		{
	envoyer = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email erron�", "");
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
			

			System.out.println("id morale "+ ctmorale.getIdContact());
		EnvoiEmailGroupe(ctmorale) ;
		System.out.println("id morale "+ ctmorale.getIdContact());
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "est bien envoy� �", ctmorale.getContact().getNom());
	envoyer = true;
	initMailinig();
	
			}
			catch(Exception e)
			{
		envoyer = false;
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email erron�", "");
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
	ctmorale.getContact().getEmail(),
	 objet,
msg);

initMailinig();

}




public UploadedFile getFile() {
    return file;
}

public void setFile(UploadedFile file) {
    this.file = file;
}





public void handleFileUpload(FileUploadEvent event) throws IOException {
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean add = false;
	
	if(event.getFile()!= null) {
    	try {
    		
   
    	     int Idct=contactDao.findAll(Contact.class).size();
    	   		Contact ct=(Contact) contactDao.findAll(Contact.class).get(Idct-1);
    	   		int id=ct.getIdContact();

    		File file2=new File("c:\\Users\\bilel\\git\\localToolsRepository\\AppContact\\src\\main\\webapp\\images\\contact\\"+id+".jpg");



    
			InputStream inputstream= event.getFile().getInputstream() ;
			moraleDao.saveFile(inputstream, file2);
			if((contact.getTelephonne()==null) ||(contact.getTelephonne()==0))
			{
				contact.setTelephonne(null);
			}
			
			
			if((contact.getMobile()==null) ||(contact.getMobile()==0))
			{
				contact.setMobile(null);
			}
//	 message = new FacesMessage("la photo ", file.getFileName() + "est enregistr�e ");


} 
catch (Exception e)

{

add = false;
	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout d'mage erron�", "");

}

//
//
//FacesContext.getCurrentInstance().addMessage(null, message);
context.addCallbackParam("add",add);
context.update("AjouterMorale:daddMorale");

AjouterMembreContactsansInit();
}

	        
	
	

}





 
public void upload() throws IOException {
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean add = false;
	
    if(file != null) {
    	try {
    		
    	     byte[] data = file.getContents();
    	     contact.setPhoto(data);
    	     int Idct=contactDao.findAll(Contact.class).size();
    	   		Contact ct=(Contact) contactDao.findAll(Contact.class).get(Idct-2);
    	   		int id=ct.getIdContact()+2;

    		File file2=new File("c:\\tmp\\"+id+".jpg");


    
			InputStream inputstream= file.getInputstream() ;
			moraleDao.saveFile(inputstream, file2);
			if((contact.getTelephonne()==null) ||(contact.getTelephonne()==0))
			{
				contact.setTelephonne(null);
			}
			
			
			if((contact.getMobile()==null) ||(contact.getMobile()==0))
			{
				contact.setMobile(null);
			}
//	 message = new FacesMessage("la photo ", file.getFileName() + "est enregistr�e ");


} 
catch (Exception e)

{

add = false;
	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout d'mage erron�", "");

}

//
//
//FacesContext.getCurrentInstance().addMessage(null, message);
context.addCallbackParam("add",add);
context.update("AjouterMorale:daddMorale");

AjouterMembreContactsansInit();
}

	        
	        
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



public Secteur getSecteur() {
	return secteur;
}


public  void setSecteur(Secteur secteur) {
	MoraleBean.secteur = secteur;
}


public  Chefresponsable getChef() {
	return chef;
}


public void setChef(Chefresponsable chef) {
	MoraleBean.chef = chef;
}


public  Produit getProduit() {
	return produit;
}


public  void setProduit(Produit produit) {
	MoraleBean.produit = produit;
}


public Region getRegion() {
	return region;
}


public void setRegion(Region region) {
	MoraleBean.region = region;
}


public  Groupecontact getGroupe() {
	return groupe;
}


public  void setGroupe(Groupecontact groupe) {
	MoraleBean.groupe = groupe;
}


public  Typemoral getType() {
	return type;
}


public  void setType(Typemoral type) {
	MoraleBean.type = type;
}


public  Contact getContact() {
	return contact;
}


public  void setContact(Contact contact) {
	MoraleBean.contact = contact;
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
	init();
	
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

	  if(selectMorale!=null)
	     {
	RequestContext context = RequestContext.getCurrentInstance();
	context.update("SelectedmailContactMorale");
	context.execute("PF('ViewEmail').show();");
//	selectMorale=null;
	     }
    else
    {
	FacesMessage message = null;


	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "il faut s�lectionner un ligne ", "");
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
     if(selectMorale!=null)
     {
	RequestContext context = RequestContext.getCurrentInstance();
	context.update("MembreMorale");
	context.execute("PF('Viewmorale').show();");
//	selectMorale=null;
     }
     
     else
     {
 	FacesMessage message = null;


	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "il faut s�lectionner un ligne ", "");
	FacesContext.getCurrentInstance().addMessage(null, message);
	RequestContext context = RequestContext.getCurrentInstance();
	context.update("formMorale:tab");
     }
     
 	
}


public void init()
{
contact=new Contact() ;
morale=new Morale();
	secteur.setLibelleSecteur("");
	secteur.setIdSecteur(null);

	region.setLibelleRegion("");
	region.setIdRegion(null);

	groupe.setLibelleGroupe("");
	groupe.setIdGroupe(null);

	type.setLibelleMorale("");
	type.setIdMoral(null);
	chef.setIdChefResponsable(null);
	chef.setAdresse("");
	chef.setEmail("");

	chef.setNomChefResponsable("");
	chef.setPrenomChefResponsable("");
	chef.setTelephone(null);
	contact.setAdresse("");
	contact.setCodePostal("");
	contact.setEmail("");
	
	contact.setNom("");
	contact.setTelephonne(null);
	contact.setMobile(null);
	contact.setVille("");
	contact.setRegion(region);
	contact.setGroupecontact(groupe);
	produit.setIdProduit(null);
	produit.setLibelle("");
	contact.setPhoto(null);

	morale.setFax(null);
	morale.setDescription("");
	morale.setProduit(produit);
	morale.setChefresponsable(chef);
	contact.setDate(null);

}






public String modifier2() throws IOException

{	
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean add = false;
	



	
	
	if((groupe!=null)&&(groupe.getIdGroupe()!=0))
	{
		contact.setGroupecontact((Groupecontact) groupecontactDao.findById(Groupecontact.class, groupe.getIdGroupe())); 	
	}
	
	else
		{contact.setGroupecontact(null);}
	
	

	contact.setSecteur((Secteur)secteurDao.findById(Secteur.class, secteur.getIdSecteur()));
	contact.setRegion((Region) regionDao.findById(Region.class,region.getIdRegion()));
	java.util.Date date = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	contact.setDate(sqlDate);
	contactDao.saveOrUpdate(contact);
	





 morale.setContact((Contact)contactDao.findById(Contact.class, contact.getIdContact()));

 morale.setTypemoral((Typemoral)typeMoralDao.findById(Typemoral.class, type.getIdMoral()));
 
 
 if((produit!=null)&&(produit.getIdProduit()!=0))
 {
 morale.setProduit((Produit)produitDao.findById(Produit.class, produit.getIdProduit()));
 }
 
 else
 {morale.setProduit(null);}
 
	if((chef!=null)&&(chef.getIdChefResponsable()!=0))
	{
 morale.setChefresponsable((Chefresponsable)chefResponsableDao.findById(Chefresponsable.class, chef.getIdChefResponsable()));
	}
	else
	{
		morale.setChefresponsable(null);
	}


try {moraleDao.saveOrUpdate(morale);


message = new FacesMessage(FacesMessage.SEVERITY_INFO, "le contact "+contact.getNom()+" est bien enregistr�", "");
add = true;

init();
return "successAjout";




} 
catch (Exception e)

{

add = false;
	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout erron�", "");
	init();
}






FacesContext.getCurrentInstance().addMessage(null, message);
context.addCallbackParam("add",add);
context.update("MembreMorale:tabMembre");


init();
return "successAjout";
}



public String ajout() throws IOException

{	
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean add = false;
	



	
	
	if((groupe!=null)&&(groupe.getIdGroupe()!=0))
	{
		contact.setGroupecontact((Groupecontact) groupecontactDao.findById(Groupecontact.class, groupe.getIdGroupe())); 	
	}
	
	else
		{contact.setGroupecontact(null);}
	
	

	contact.setSecteur((Secteur)secteurDao.findById(Secteur.class, secteur.getIdSecteur()));
	contact.setRegion((Region) regionDao.findById(Region.class,region.getIdRegion()));
	java.util.Date date = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	contact.setDate(sqlDate);
	contactDao.saveOrUpdate(contact);
	





 morale.setContact((Contact)contactDao.findById(Contact.class, contact.getIdContact()));

 morale.setTypemoral((Typemoral)typeMoralDao.findById(Typemoral.class, type.getIdMoral()));
 
 
 if((produit!=null)&&(produit.getIdProduit()!=0))
 {
 morale.setProduit((Produit)produitDao.findById(Produit.class, produit.getIdProduit()));
 }
 
 else
 {morale.setProduit(null);}
 
	if((chef!=null)&&(chef.getIdChefResponsable()!=0))
	{
 morale.setChefresponsable((Chefresponsable)chefResponsableDao.findById(Chefresponsable.class, chef.getIdChefResponsable()));
	}
	else
	{
		morale.setChefresponsable(null);
	}


try {moraleDao.saveOrUpdate(morale);


message = new FacesMessage(FacesMessage.SEVERITY_INFO, "le contact "+contact.getNom()+" est bien enregistr�", "");
add = true;

init();
return "successAjout";




} 
catch (Exception e)

{

add = false;
	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout erron�", "");
	init();
}






FacesContext.getCurrentInstance().addMessage(null, message);
context.addCallbackParam("add",add);
context.update("MembreMorale:tabMembre");


init();
return "successAjout";
}


public void modifier(Morale moralEdit)
{
	
	morale=(Morale) moraleDao.findById(Morale.class, moralEdit.getIdContact());
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

	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "il faut s�lectionner un ligne ", "");

}
else
{
moraleDao.delete(morale);

	message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Supprim�", morale.getContact().getNom());
	deleted = true;
}
	
}catch (Exception e)

{
	deleted = false;
	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Supression erron�", "");
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





public  Morale getSelectMorale() {

	return selectMorale;
}



public  void setSelectMorale(Morale selectMorale) {
	
	MoraleBean.selectMorale = selectMorale;
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
		if(selectMorale.getIdContact()!=null)
		{

	
 list= membreDao.findByCriteria(Membre.class, critere1,selectMorale.getIdContact()) ;
	
	}
		selectMorale=null;
		return list;
	}
	
	catch(Exception e)
	{
		
	}
	return list;
	
	
	
	
	
	
}





public void onRowEdit(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Car Edited", ((Morale) event.getObject()).toString());
Morale moralEdit = (Morale) event.getObject() ;
morale=(Morale) moraleDao.findById(Morale.class, moralEdit.getIdContact());

System.out.println("id contact "+ morale.getIdContact());
System.out.println("nom contact "+ moralEdit.getContact().getNom());
    moraleDao.saveOrUpdate(morale);
    
    FacesContext.getCurrentInstance().addMessage(null, msg);
}
 
public void onRowCancel(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Edit Cancelled", ((Morale) event.getObject()).toString());
    FacesContext.getCurrentInstance().addMessage(null, msg);
}
 
public void onCellEdit(CellEditEvent event) {
    Object oldValue = event.getOldValue();
    Object newValue = event.getNewValue();
    

    System.out.println("nom contact "+ oldValue.toString());
    System.out.println("nom contact "+ newValue.toString());
     
    if(newValue != null && !newValue.equals(oldValue)) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}




public String modifierMorale()
{
	
	if(selectMorale!=null)
		return "successM" ;
	else
		return "s" ;
}




public String modifier1() throws IOException

{	
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean add = false;
	


//groupe=selectMorale.getContact().getGroupecontact();
//	
//	
//	if((groupe!=null)&&(groupe.getIdGroupe()!=0))
//	{
//		contact.setGroupecontact((Groupecontact) groupecontactDao.findById(Groupecontact.class, groupe.getIdGroupe())); 	
//	}
//	
//	else
//		{contact.setGroupecontact(null);}
//	
//	
//
//	contact.setSecteur((Secteur)secteurDao.findById(Secteur.class, secteur.getIdSecteur()));
//	contact.setRegion((Region) regionDao.findById(Region.class,region.getIdRegion()));
//	java.util.Date date = new java.util.Date();
//	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//	contact.setDate(sqlDate);

//	
//
//
//
//
//
// morale.setContact((Contact)contactDao.findById(Contact.class, contact.getIdContact()));
//
// morale.setTypemoral((Typemoral)typeMoralDao.findById(Typemoral.class, type.getIdMoral()));
// 
// 
// if((produit!=null)&&(produit.getIdProduit()!=0))
// {
// morale.setProduit((Produit)produitDao.findById(Produit.class, produit.getIdProduit()));
// }
// 
// else
// {morale.setProduit(null);}
// 
//	if((chef!=null)&&(chef.getIdChefResponsable()!=0))
//	{
// morale.setChefresponsable((Chefresponsable)chefResponsableDao.findById(Chefresponsable.class, chef.getIdChefResponsable()));
//	}
//	else
//	{
//		morale.setChefresponsable(null);
//	}


try {
	
//	contact=selectMorale.getContact();
//	contactDao.saveOrUpdate(contact);
//	moraleDao.saveOrUpdate(selectMorale);

//
//message = new FacesMessage(FacesMessage.SEVERITY_INFO, "le contact "+selectMorale.getContact().getNom()+" est bien enregistr�", "");
add = true;

//init();
return "successModif";




} 
catch (Exception e)

{

//add = false;
//	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout erron�", "");
//	init();
}






//FacesContext.getCurrentInstance().addMessage(null, message);
//context.addCallbackParam("add",add);
//context.update("formMorale:tab");


//init();
return "successModif";
}



	
}
