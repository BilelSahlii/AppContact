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

import com.idao.GroupeContactDao;




@ManagedBean(name="groupeContactBean")
@SessionScoped
@Scope(value="session")



public class GroupeContactBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Groupecontact> list_GroupeContact;
public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application-Context.xml");;
static	public GroupeContactDao groupecontactDao=(GroupeContactDao) context.getBean("GroupeContactDao");

static private Groupecontact groupecontact=new Groupecontact();
 private static Groupecontact selectGroupecontact=new Groupecontact();




 public  Groupecontact getGroupecontact() {
		return groupecontact;
	}




	public  void setGroupecontact(Groupecontact groupecontact) {
		GroupeContactBean.groupecontact = groupecontact;
	}


 
 
 
 

public void initG()
{	groupecontact=new Groupecontact();
	groupecontact.setIdGroupe(null);
	groupecontact.setLibelleGroupe("");

}

public void ajout()

{	

	groupecontactDao.saveOrUpdate(groupecontact);
	initG();
	

}



public void suprimerAgent(Groupecontact groupecontact)
{
	//agentdevise=getSelectagentdevise();
	

	groupecontactDao.delete(groupecontact);
}










public List<Groupecontact> getList_GroupeContact() {
    List list=groupecontactDao.findAll(Groupecontact.class);
	return list;
}




public void setList_GroupeContact(List<Groupecontact> list_GroupeContact) {
	this.list_GroupeContact = list_GroupeContact;
}







public static Groupecontact getSelectGroupecontact() {
	return selectGroupecontact;
}




public static void setSelectGroupecontact(Groupecontact selectGroupecontact) {
	GroupeContactBean.selectGroupecontact = selectGroupecontact;
}




	
}
