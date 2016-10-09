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

import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.w3c.dom.events.EventException;

import com.model.Chefresponsable;

import com.model.Groupecontact;
import com.model.Morale;
import com.model.Physique;

import com.model.Region;
import com.idao.ChefResponsableDao;
import com.idao.MoraleDao;
import com.idao.GroupeContactDao;
import com.idao.PhysiqueDao;

import com.idao.RegionDao;



@ManagedBean(name="chefResponsableBean")
@SessionScoped
@Scope(value="session")



public class ChefResponsableBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Chefresponsable> list_ChefResponsable;
public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");;
static	public ChefResponsableDao chefResponsableDao=(ChefResponsableDao) context.getBean("ChefResponsableDao");

static private Chefresponsable chefresponsable =new Chefresponsable();
 private static Chefresponsable selectChefresponsable;

 
 private UploadedFile file;

 public UploadedFile getFile() {
     return file;
 }

 public void setFile(UploadedFile file) {
     this.file = file;
 }
 
 void initchef()
{
	 chefresponsable =new Chefresponsable();
	
}

 
 
 
 
	void insererImage(Chefresponsable chefImg) throws IOException
	{



		try {

			File file2=new File("c:\\Users\\bilel\\git\\localToolsRepository\\AppContact\\src\\main\\webapp\\images\\ChefResponsable\\"+chefImg.getIdChefResponsable()+".jpg");


		    
			InputStream inputstream= file.getInputstream() ;
			chefResponsableDao.saveFile(inputstream, file2);
			if((chefresponsable.getTelephone()==null) ||(chefresponsable.getTelephone()==0))
			{
				chefresponsable.setTelephone(null);
			}
		}
		catch (Exception e)

		{



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

	//
	//
	FacesContext.getCurrentInstance().addMessage(null, message);
	context.addCallbackParam("add",add);
	context.update("ajouterChef:addChef");



	}

		        
		
		

	}



 
 



 
 
 

public void ajout() throws IOException

{	





RequestContext context = RequestContext.getCurrentInstance();
FacesMessage message = null;
boolean add = false;


try {

	chefResponsableDao.saveOrUpdate(chefresponsable);
	insererImage(chefresponsable);
message = new FacesMessage(FacesMessage.SEVERITY_INFO, "le chef responsable  "+chefresponsable.getPrenomChefResponsable()+" "+chefresponsable.getNomChefResponsable()+" est bien enregistré", "");
add = true;


initchef();

} 
catch (Exception e)

{

add = false;
message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout erroné", "");
initchef();

}

FacesContext.getCurrentInstance().addMessage(null, message);
context.addCallbackParam("add",add);
context.update("AjouterMorale:panelAdd");
initchef();



}



public void modifier()

{	chefResponsableDao.saveOrUpdate(chefresponsable);

}


public void onRowEdit(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Car Edited", ((Chefresponsable) event.getObject()).toString());
    


    chefResponsableDao.saveOrUpdate(chefresponsable);


    
    FacesContext.getCurrentInstance().addMessage(null, msg);
}
 
public void onRowCancel(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Edit Cancelled", ((Chefresponsable) event.getObject()).toString());
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



public void suprimer(Chefresponsable chefresponsable)
{
	chefResponsableDao.delete(chefresponsable);
}







public static ChefResponsableDao getChefResponsableDao() {
	return chefResponsableDao;
}



public static void setChefResponsableDao(ChefResponsableDao chefResponsableDao) {
	ChefResponsableBean.chefResponsableDao = chefResponsableDao;
}



public List<Chefresponsable> getList_ChefResponsable() {
	List list=chefResponsableDao.findAll(Chefresponsable.class);
	
	return list;

}



public void setList_ChefResponsable(List<Chefresponsable> list_ChefResponsable) {
	this.list_ChefResponsable = list_ChefResponsable;
}



public  Chefresponsable getChefresponsable() {
	return chefresponsable;
}



public void setChefresponsable(Chefresponsable chefresponsable) {
	ChefResponsableBean.chefresponsable = chefresponsable;
}



public  Chefresponsable getSelectChefresponsable() {
	return selectChefresponsable;
}



public static void setSelectChefresponsable(
		Chefresponsable selectChefresponsable) {
	ChefResponsableBean.selectChefresponsable = selectChefresponsable;
}






	
}
