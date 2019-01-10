package com.m2i.tp.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.m2i.tp.entity.Compte;
import com.m2i.tp.service.ServiceCompte;

public class MyApp {

	public static void main(String[] args) {
		ApplicationContext contextSpring =
				new ClassPathXmlApplicationContext("mySpringConf.xml");
		ServiceCompte s = (ServiceCompte) contextSpring.getBean("serviceCompteImpl");
		               //ou bien contextSpring.getBean(ServiceCompte.class);
		
		//ne surtout pas ecrire s= new ServiceCompteImpl() ; !!!!!!
		
		Compte cpt = new Compte(null,"compte 1",100.0);
		s.saveOrUpdateCompte(cpt);
		System.out.println("cpt="+cpt.toString());
	}

}
