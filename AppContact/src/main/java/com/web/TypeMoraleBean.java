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
import com.model.Produit;
import com.model.Region;
import com.model.Secteur;
import com.model.Typemoral;
import com.idao.MoraleDao;
import com.idao.GroupeContactDao;
import com.idao.PhysiqueDao;
import com.idao.ProduitDao;
import com.idao.RegionDao;
import com.idao.SecteurDao;
import com.idao.TypeMoralDao;



@ManagedBean(name="typeMoraleBean")
@SessionScoped
@Scope(value="session")



public class TypeMoraleBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Typemoral> list_TypeMoral;
public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");;
static	public TypeMoralDao typeMoralDao=(TypeMoralDao) context.getBean("TypeMoralDao");

static private Typemoral typemoral=new Typemoral();
 private static Typemoral selectTypemoral;

 
 

void initM()
{
	typemoral=new Typemoral() ;
	typemoral.setLibelleMorale("");
	typemoral.setIdMoral(null);
}





public void ajout() throws IOException

{	
	




RequestContext context = RequestContext.getCurrentInstance();
FacesMessage message = null;
boolean add = false;


try {
	typeMoralDao.saveOrUpdate(typemoral);

message = new FacesMessage(FacesMessage.SEVERITY_INFO, "le type "+typemoral.getLibelleMorale()+" est bien enregistré", "");
add = true;


initM();

} 
catch (Exception e)

{

add = false;
message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout erroné", "");
initM();

}

FacesContext.getCurrentInstance().addMessage(null, message);
context.addCallbackParam("add",add);
context.update("AjouterMorale:panelAdd");
initM();

}







public void suprimer(Typemoral typemoral)
{
	typeMoralDao.delete(typemoral);
}





public List<Typemoral> getList_TypeMoral() {
	List list=typeMoralDao.findAll(Typemoral.class);
	return list;

}



public void setList_TypeMoral(List<Typemoral> list_TypeMoral) {
	this.list_TypeMoral = list_TypeMoral;
}



public  Typemoral getTypemoral() {
	return typemoral;
}



public  void setTypemoral(Typemoral typemoral) {
	TypeMoraleBean.typemoral = typemoral;
}



public static Typemoral getSelectTypemoral() {
	return selectTypemoral;
}



public static void setSelectTypemoral(Typemoral selectTypemoral) {
	TypeMoraleBean.selectTypemoral = selectTypemoral;
}



	
}
