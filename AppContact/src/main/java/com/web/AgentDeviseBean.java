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

import com.model.Agentdevise;

import com.idao.AgentDeviseDao;




@ManagedBean(name="agentDeviseBean")
@SessionScoped
@Scope(value="session")



public class AgentDeviseBean implements Serializable {
private static final long serialVersionUID = -954873828500119177L;
private	List <Agentdevise> list_AgentDevise;
public static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Application.xml");;
static	public AgentDeviseDao agentDao=(AgentDeviseDao) context.getBean("agentDeviseDao");


 private static Agentdevise selectagentdevise=new Agentdevise();

 






	







public  Agentdevise getSelectagentdevise() {
	return selectagentdevise;
}




public  void setSelectagentdevise(Agentdevise selectagentdevise) {
	AgentDeviseBean.selectagentdevise = selectagentdevise;
}





//public void onRowEdit(RowEditEvent event) {
//	
//
//	
//Agentdevise agent = (Agentdevise) event.getObject();
//
//	
//	
//
//    System.out.println("event edit"+agent.getNom());
//	agentDao.saveOrUpdate(agent);
// 
//    FacesMessage msg = new FacesMessage("agnet edité", ((Agentdevise) event.getObject()).getNom());
//    FacesContext.getCurrentInstance().addMessage(null, msg);
//	
//	
//
//
//}



// 
//public void onRowCancel(RowEditEvent event) {
//
//	
//
//	
//	
//
//	
//    FacesMessage msg = new FacesMessage("Edit Cancelled");
//    FacesContext.getCurrentInstance().addMessage(null, msg);
//    
//
//	
//    
//    
//}
//
//
//
//
// 
//public void onCellEdit(CellEditEvent event) {
//
//    Object oldValue = event.getOldValue();
//    
//    Object newValue = event.getNewValue();
//	System.out.println("b1 bilel");
//
//	
//	
//	agentDao.saveOrUpdate(agentdevise);
//    
//
//    
//    
//   
//    if(newValue != null && !newValue.equals(oldValue)) {
//    	
//    	DataTable tableAgent= (DataTable) event.getSource();
//    	agentdevise =(Agentdevise)tableAgent.getRowData();
//    	 try{ 
//    	
//    	
//    	System.out.println(agentdevise.getNcin());
//   	
//    	
//    	
//    	agentDao.saveOrUpdate(agentdevise);
//        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
// 
//    catch(EventException e){
//    	System.out.println("le'autre fois");
//    	System.out.println(e.getMessage());}
//    }  
//}






public void onEditEvent(RowEditEvent event)
{
	agentDao.saveOrUpdate((Agentdevise)event.getObject());
	addMessage("Info", "Devise modifiée");
	
}


public void onConcel()

{
	addMessage("Info", "modifications annulées");
	
}

private void addMessage(String summary,String detail)
{
	FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_INFO,summary, detail);
}







public List<Agentdevise> getList_AgentDevise() {

    List list=agentDao.findAll(Agentdevise.class);

 
 
	
	return list;
}


public void init(Agentdevise agent)
		{
	agent.setNcin(00);
	agent.setEmail("");
	agent.setAge(00);
	agent.setNom("");
	agent.setPassword("");
	agent.setPrenom("");
		}
 
public void ajout()

{	

	agentDao.saveOrUpdate(selectagentdevise);


	
}



public void suprimerAgent(Agentdevise agentdevise)
{
	//agentdevise=getSelectagentdevise();
	

	agentDao.delete(agentdevise);
}






public void setList_AgentDevise(List<Agentdevise> list_AgentDevise) {
	this.list_AgentDevise = list_AgentDevise;
}






public static AgentDeviseDao getAgentDao() {
	return agentDao;
}






public static void setAgentDao(AgentDeviseDao agentDao) {
	AgentDeviseBean.agentDao = agentDao;
}










public List<Agentdevise> recupererListAgentDevise() {
	List list=agentDao.findAll(Agentdevise.class);
    Iterator iter=list.iterator();
	return list;
	
}


	
}
