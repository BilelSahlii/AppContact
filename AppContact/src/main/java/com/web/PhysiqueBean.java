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
import com.model.Morale;
import com.model.Physique;
import com.idao.MoraleDao;
import com.idao.GroupeContactDao;
import com.idao.PhysiqueDao;



@ManagedBean(name="physiqueBean")
@SessionScoped
@Scope(value="session")



public class PhysiqueBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Physique> list_Physique;
public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");;
static	public PhysiqueDao physiqueDao=(PhysiqueDao) context.getBean("PhysiqueDao");

static private Physique physique = new Physique();
 private static Physique selectPhysique;

 
 



public void ajout(Physique physique)

{	
	physiqueDao.saveOrUpdate(physique);
}



public void suprimer(Physique physique)
{
	physiqueDao.delete(physique);
}



public List<Physique> getList_Physique() {
  List list=physiqueDao.findAll(Physique.class);
	return list;
}



public void setList_Physique(List<Physique> list_Physique) {
	this.list_Physique = list_Physique;
}



public static Physique getPhysique() {
	return physique;
}



public static void setPhysique(Physique physique) {
	PhysiqueBean.physique = physique;
}



public static Physique getSelectPhysique() {
	return selectPhysique;
}



public static void setSelectPhysique(Physique selectPhysique) {
	PhysiqueBean.selectPhysique = selectPhysique;
}






	
}
