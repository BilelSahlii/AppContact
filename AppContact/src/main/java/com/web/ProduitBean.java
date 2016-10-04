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
import com.idao.MoraleDao;
import com.idao.GroupeContactDao;
import com.idao.PhysiqueDao;
import com.idao.ProduitDao;



@ManagedBean(name="produitBean")
@SessionScoped
@Scope(value="session")



public class ProduitBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Produit> list_Produit;
private	List <Produit> selectedlist_Produit;
public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");;
static	public ProduitDao produitDao=(ProduitDao) context.getBean("ProduitDao");

 private static Produit produit= new Produit();
 private static Produit selectProduit=new Produit();
 

 
 
 public Produit getProduit() {
		return produit;
	}



	public List<Produit> getSelectedlist_Produit() {
	return selectedlist_Produit;
}



public void setSelectedlist_Produit(List<Produit> selectedlist_Produit) {
	this.selectedlist_Produit = selectedlist_Produit;
}



	public  void setProduit(Produit produit) {
		ProduitBean.produit = produit;
	}



public void initP()
{
	produit=new Produit();

	
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
	produitDao.saveOrUpdate(produit);

message = new FacesMessage(FacesMessage.SEVERITY_INFO, "l'activité "+produit.getLibelle()+" est bien enregistré", "");
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
context.update("AjouterMorale:addmorale");

initP();
AjouterMembreContact();
}


public void suprimer(Produit produit)
{
	produitDao.delete(produit);
}



public List<Produit> getList_Produit() {
	List list=produitDao.findAll(Produit.class);
return list;
}



public void setList_Produit(List<Produit> list_Produit) {
	this.list_Produit = list_Produit;
}




public  Produit getSelectProduit() {
	return selectProduit;
}



public  void setSelectProduit(Produit selectProduit) {
	ProduitBean.selectProduit = selectProduit;
}









	
}
