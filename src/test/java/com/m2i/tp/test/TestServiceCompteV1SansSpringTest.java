package com.m2i.tp.test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.m2i.tp.entity.Compte;
//org.junit = Junit4
import com.m2i.tp.service.ServiceCompte;

//Classe de test unitaire (framework JUnit 4)
//nécessite junit 4 dans le pom.xml
public class TestServiceCompteV1SansSpringTest {
	private ServiceCompte serviceCompte=null ; //à tester
	private static ApplicationContext contextSpring =null;
	
	@BeforeClass //méthode (re-)lancée avec chaque @Test
	public static void preparerChoseStatic() {
		contextSpring =
				new ClassPathXmlApplicationContext("mySpringConf.xml");
	}
	
	@AfterClass
	public static void finaliserChoseStatic() {
		((ClassPathXmlApplicationContext)contextSpring).close();
	}
	
	@Before //méthode (re-)lancée avec chaque @Test
	public void preparerServiceCompte() { 
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
