package com.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.w3c.dom.events.EventException;

import com.model.Groupecontact;
import com.model.Membre;
import com.model.Morale;
import com.model.Physique;
import com.util.MailMail;
import com.idao.MoraleDao;
import com.idao.GroupeContactDao;
import com.idao.PhysiqueDao;



@ManagedBean(name="physiqueBean")
@SessionScoped
@Scope(value="session")



public class PhysiqueBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Physique> list_Physique;
public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");
private static ApplicationContext contextEmail = new ClassPathXmlApplicationContext(
		"Spring-Mail.xml");
static public MailMail mm = (MailMail) contextEmail.getBean("mailMail");
static	public PhysiqueDao physiqueDao=(PhysiqueDao) context.getBean("PhysiqueDao");

static private Physique physique = new Physique();

 private static Physique selectPhysique;
 
	private List<Physique> selected_list_Physique;
	

 
 public List<Physique> getSelected_list_Physique() {
		return selected_list_Physique;
	}

	public void setSelected_list_Physique(List<Physique> selected_list_Physique) {
		this.selected_list_Physique = selected_list_Physique;
	}

static private UploadedFile file;

 public String getDestinateur() {
		return destinateur;
		
	}

	public void setDestinateur(String destinateur) {
		this.destinateur = destinateur;
	}

	private String destinateur;
	private String objet;
	private String msg;
 
 
 
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

	void insererImage(Physique phImg) throws IOException {

		File file2 = new File(
				"c:\\Users\\bilel\\git\\localToolsRepository\\AppContact\\src\\main\\webapp\\images\\contactPhysiqie\\"
						+ phImg.getIdPhysique() + ".jpg");

		try {

			InputStream inputstream = file.getInputstream();
		physiqueDao.saveFile(inputstream, file2);


		} catch (Exception e)

		{

		}

	}



	public void init() {

physique= new Physique();
		physique=null ;
		file = null;

	}
	
public String  ajout()

{	
	
	

	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean add = false;

	try {
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	physique.setDate(sqlDate);
		physiqueDao.saveOrUpdate(physique);
		insererImage(physique);
		return "successAcceuil" ;




	} catch (Exception e)

	{

		add = false;
		message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"transaction erroné", "");
		init();
		return "" ;
	}




	

	

	
	
}



public void suprimer(Physique physique)
{
	
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean deleted = false;

	try {
		physiqueDao.delete(physique);


			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Supprimé", physique.getNom()+" "+physique.getPrenom());
			deleted = true;


	} catch (Exception e)

	{
		deleted = false;
		message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Supression erroné", "");
	}

	FacesContext.getCurrentInstance().addMessage(null, message);
	context.addCallbackParam("deleted", deleted);
	context.update("formPh:tab");

	
	

}



public List<Physique> getList_Physique() {
  List list=physiqueDao.findAll(Physique.class);
	return list;
}



public void setList_Physique(List<Physique> list_Physique) {
	this.list_Physique = list_Physique;
}



public  Physique getPhysique() {
	return physique;
}



public  void setPhysique(Physique physique) {
	PhysiqueBean.physique = physique;
}



public  Physique getSelectPhysique() {
	return selectPhysique;
}



public  void setSelectPhysique(Physique selectPhysique) {
	PhysiqueBean.selectPhysique = selectPhysique;
}


public String versModifierAjout() {


		return "successPhysique";

}


public String retourAcceuil() {


	return "Accuile";

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

void initMailinig() {
	selected_list_Physique = null;
	physique = null;
	msg = "";
	destinateur = "";
	objet = "";
}

public void EnvoiEmail() {
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean envoyer = false;

	MailMail mm = (MailMail) contextEmail.getBean("mailMail");

	try {
		if (physique == null) {
			envoyer = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Il faut selectionner un contact", "");
		}

		else {
			mm.sendMail("bilell.sahli@gmail.com", physique.getEmail(), objet,
					msg);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"est bien envoyé à", physique.getNom());
			envoyer = true;

		}
	} catch (Exception e) {
		envoyer = false;
		message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Email erroné", "");
		initMailinig();
	}

	FacesContext.getCurrentInstance().addMessage(null, message);
	context.addCallbackParam("envoyer", envoyer);
	context.update("formPh:tab");
	initMailinig();

}

public void EnvoiGroupe() {
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean envoyer = false;
	List<Physique> list =selected_list_Physique;
	Iterator<Physique> iter = list.iterator();
	if (!iter.hasNext()) {

		return;
	} else

	{
		while (iter.hasNext()) {

			try {
				Physique ctmorale = (Physique) iter.next();

				System.out.println("id morale " + ctmorale.getIdPhysique());
				EnvoiEmailGroupe(ctmorale);
				System.out.println("id morale " + ctmorale.getIdPhysique());
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"est bien envoyé à", ctmorale.getNom());
				envoyer = true;
				initMailinig();

			} catch (Exception e) {
				envoyer = false;
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Email erroné", "");
				initMailinig();
			}

			FacesContext.getCurrentInstance().addMessage(null, message);
			context.addCallbackParam("envoyer", envoyer);
			context.update("formMorale:tab");
			initMailinig();

		}

	}
}

public void EnvoiEmailGroupe(Physique ctmorale) {

	MailMail mm = (MailMail) contextEmail.getBean("mailMail");
	mm.sendMail("bilell.sahli@gmail.com", ctmorale.getEmail(), objet, msg);

	initMailinig();

}
public void EmmailingGroupe() throws IOException {

	RequestContext context = RequestContext.getCurrentInstance();
	context.update("edmailGroupePh");
	context.execute("PF('ViewEmailGPh').show();");
}


	
}
