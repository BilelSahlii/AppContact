package com.util;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.model.Contact;


@ManagedBean(name="appEmail")
@SessionScoped
@Scope(value="session")
public class App
{

	private static ApplicationContext context ;
	private static Contact selectcontact ;
	private String objet ;
	private String msg ;
	
	
    	
    	
    	
    	
    	public static Contact getSelectcontact() {
		return selectcontact;
	}

	public static void setSelectcontact(Contact selectcontact) {
		App.selectcontact = selectcontact;
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

		@PostConstruct
    	public void init() {
    		context=       new ClassPathXmlApplicationContext("Spring-Mail.xml");
    		selectcontact=new Contact();
    		objet="" ;
    		msg="";
             
    	}
    	
    public void EnvoiEmail()
    {
    	
    

    	MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMail("bilell.sahli@gmail.com",
    		   "bilell.sahli@gmail.com",
    		   "Testing123",
    		   "Testing only \n\n Hello Spring Email Sender");

    }
}