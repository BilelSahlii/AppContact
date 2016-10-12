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
import com.model.Activite;
import com.idao.ActiviteDao;
import com.idao.MoraleDao;
import com.idao.GroupeContactDao;
import com.idao.PhysiqueDao;




@ManagedBean(name="activiteBean")
@SessionScoped
@Scope(value="session")



public class ActiviteBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Activite> list_Activite;
private	List <Activite> selectedlist_Activite;
public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");;
static	public ActiviteDao activiteDao=(ActiviteDao) context.getBean("ActiviteDao");

 private static Activite activite= new Activite();
 private static Activite selectActivite=new Activite();
 

 
 



public List<Activite> getList_Activite() {
	List list=activiteDao.findAll(Activite.class);
return list;
}





public void setList_Activite(List<Activite> list_Activite) {
	this.list_Activite = list_Activite;
}





public List<Activite> getSelectedlist_Activite() {
	return selectedlist_Activite;
}





public void setSelectedlist_Activite(List<Activite> selectedlist_Activite) {
	this.selectedlist_Activite = selectedlist_Activite;
}





public Activite getActivite() {
	return activite;
}





public  void setActivite(Activite activite) {
	activite = activite;
}





public Activite getSelectActivite() {
	return selectActivite;
}





public void setSelectActivite(Activite selectActivite) {
ActiviteBean.selectActivite = selectActivite;
}





public void initP()
{
	activite=new Activite();

	
}





public void ajout() throws IOException

{	

RequestContext context = RequestContext.getCurrentInstance();
FacesMessage message = null;
boolean add = false;


try {
	activiteDao.saveOrUpdate(activite);

message = new FacesMessage(FacesMessage.SEVERITY_INFO, "l'activité "+activite.getLibelle()+" est bien enregistré", "");
add = true;


initP();


} 
catch (Exception e)

{

add = false;
message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout erroné", "");
initP();

}

FacesContext.getCurrentInstance().addMessage(null, message);
context.addCallbackParam("add",add);
context.update("AjouterMorale:panelAdd");

initP();

}


public void suprimer(Activite activite)
{
	
	
	activiteDao.delete(activite);
}



















	
}
