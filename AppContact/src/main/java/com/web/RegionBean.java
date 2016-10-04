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
import com.idao.MoraleDao;
import com.idao.GroupeContactDao;
import com.idao.PhysiqueDao;
import com.idao.ProduitDao;
import com.idao.RegionDao;



@ManagedBean(name="regionBean")
@SessionScoped
@Scope(value="session")



public class RegionBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Region> list_Region;
public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");;
static	public RegionDao regionDao=(RegionDao) context.getBean("RegionDao");

 private static Region region=new Region();
 private static Region selectRegion;

 
 
 
 
 
 public static RegionDao getRegionDao() {
	return regionDao;
}


public static void setRegionDao(RegionDao regionDao) {
	RegionBean.regionDao = regionDao;
}


public Region getRegion() {
	return region;
}


public  void setRegion(Region region) {
	RegionBean.region = region;
}


public void init()
 {
 	region=new Region();
	 	region.setIdRegion(null);
 	region.setLibelleRegion("");


 }



public void AjouterMembreContact() throws IOException {

	RequestContext context = RequestContext.getCurrentInstance();
	context.update("AjouterMorale");
	context.execute("PF('addmorale').show();");

	
}



public void ajout() throws IOException

{	
	RequestContext context = RequestContext.getCurrentInstance();
	FacesMessage message = null;
	boolean add = false;
	

try {
	regionDao.saveOrUpdate(region);

message = new FacesMessage(FacesMessage.SEVERITY_INFO, "la region "+region.getLibelleRegion()+" est bien enregistré", "");
add = true;


init();


} 
catch (Exception e)

{

add = false;
	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ajout erroné", "");
	init();
	
}



FacesContext.getCurrentInstance().addMessage(null, message);
context.addCallbackParam("add",add);
context.update("AjouterMorale:addmorale");

init();
AjouterMembreContact();
}






public void suprimer(Region region)
{
	regionDao.delete(region);
}





public List<Region> getList_Region() {
List list=regionDao.findAll(Region.class);
return list;
}



public void setList_Region(List<Region> list_Region) {
	this.list_Region = list_Region;
}




public static Region getSelectRegion() {
	return selectRegion;
}



public static void setSelectRegion(Region selectRegion) {
	RegionBean.selectRegion = selectRegion;
}




	
}
