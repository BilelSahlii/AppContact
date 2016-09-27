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

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.w3c.dom.events.EventException;

import com.model.Contact;
import com.model.Groupecontact;
import com.model.Morale;
import com.model.Region;
import com.model.Secteur;
import com.util.MailMail;
import com.idao.ChefResponsableDao;
import com.idao.ContactDao;
import com.idao.GroupeContactDao;
import com.idao.MoraleDao;
import com.idao.ProduitDao;
import com.idao.RegionDao;
import com.idao.SecteurDao;
import com.idao.TypeMoralDao;






@ManagedBean(name="ContactBean")
@SessionScoped
@Scope(value="session")



public class ContactBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Contact> list_Contact;





private	List <Contact> selectlist_Contact;


public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");
private static ApplicationContext contextEmail = new ClassPathXmlApplicationContext("Spring-Mail.xml"); 
static public  MailMail mm = (MailMail) contextEmail.getBean("mailMail");
static	public ContactDao contactDao=(ContactDao) context.getBean("ContactDao");

static private Contact contact=new Contact();
 private static Contact selectContact=new Contact();


 static	public GroupeContactDao groupecontactDao=(GroupeContactDao) context.getBean("GroupeContactDao");

 static	public RegionDao regionDao=(RegionDao) context.getBean("RegionDao");
 static	public SecteurDao secteurDao=(SecteurDao) context.getBean("SecteurDao");


 //static private Morale morale=new Morale();

  private static Secteur secteur=new Secteur();


private static Region region=new Region();

private static Groupecontact groupe=new Groupecontact();


private Integer age ;


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




 
 
	






  public  Region getRegion() {
	return region;
}




public  void setRegion(Region region) {
	ContactBean.region = region;
}




public  Groupecontact getGroupe() {
	return groupe;
}




public  void setGroupe(Groupecontact groupe) {
	ContactBean.groupe = groupe;
}




public Integer getAge() {
	return age;
}




public void setAge(Integer age) {
	this.age = age;
}




public  Secteur getSecteur() {
	return secteur;
}




public  void setSecteur(Secteur secteur) {
	ContactBean.secteur = secteur;
}


  
public  Contact getContact() {
	return contact;
}




public  void setContact(Contact contact) {
	ContactBean.contact = contact;
}




public  Contact getSelectContact() {
	return selectContact;
}




public  void setSelectContact(Contact selectContact) {
	ContactBean.selectContact = selectContact;
}








public void EnvoiGroupe()
{
	List<Contact> list= selectlist_Contact;
	Iterator<Contact> iter = list.iterator();
	if (!iter.hasNext()) { 

		return;
	}
		else
	
		{
			while (iter.hasNext()) {

				
	Contact ct = (Contact) iter.next();
	

	EnvoiEmailGroupe(ct) ;
    FacesMessage message = new FacesMessage("Succesful is send.");
    FacesContext.getCurrentInstance().addMessage(null, message);		
		}
		}
	
}


public void EnvoiEmailGroupe(Contact ct)
{
	


MailMail mm = (MailMail) contextEmail.getBean("mailMail");
mm.sendMail("bilell.sahli@gmail.com",
	ct.getEmail(),
	 "test",
	"tets");

msg="";
destinateur="";
objet="";

}




public List<Contact> getList_Contact() {

    List list=contactDao.findAll(Contact.class);
    
	return list;
}


public void setList_Contact(List<Contact> list_Contact) {
	this.list_Contact = list_Contact;
}





public List<Contact> getSelectlist_Contact() {
	return selectlist_Contact;
}




public void setSelectlist_Contact(List<Contact> selectlist_Contact) {
	this.selectlist_Contact = selectlist_Contact;
}



public void ajout()

{


	
	contact.setGroupecontact((Groupecontact) groupecontactDao.findById(Groupecontact.class, groupe.getIdGroupe())); 
	contact.setSecteur((Secteur)secteurDao.findById(Secteur.class, secteur.getIdSecteur()));
	contact.setRegion((Region) regionDao.findById(Region.class,region.getIdRegion()));
	contactDao.saveOrUpdate(contact);
	
	System.out.println("secteur "+secteur.getIdSecteur());
	System.out.println("groupe "+groupe.getIdGroupe());
	System.out.println("region "+region.getIdRegion());
	
}



public void suprimerContact(Contact contact)
{
	//agentdevise=getSelectagentdevise();
	contactDao.delete(contact);
}





	
}
