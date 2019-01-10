package com.m2i.tp.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.m2i.tp.entity.Compte;
//org.junit = Junit4
import com.m2i.tp.service.ServiceCompte;

//Classe de test unitaire (framework JUnit 4)
//nécessite junit 4 dans le pom.xml
public class TestServiceCompte {
	
	private ServiceCompte serviceCompte=null ; //à tester
	
	@Before //méthode (re-)lancée avec chaque @Test
	public void preparerServiceCompte() {
		ApplicationContext contextSpring =
				new ClassPathXmlApplicationContext("mySpringConf.xml");
		this.serviceCompte = (ServiceCompte) contextSpring.getBean("serviceCompteImpl");
		               //ou bien contextSpring.getBean(ServiceCompte.class);
	}
	
	
	@Test
	public void testAjoutCompte() {
		Compte cpt = new Compte(null,"compte 1",100.0);
		this.serviceCompte.saveOrUpdateCompte(cpt);
		Assert.assertTrue(cpt.getNumero()!=null);
		System.out.println("cpt="+cpt.toString());
	}
	
	@Test
    public void test2() {
		Assert.assertTrue(1+1==2);
	}

}
