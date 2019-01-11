package com.m2i.tp.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.m2i.tp.entity.Compte;
//org.junit = Junit4
import com.m2i.tp.service.ServiceCompte;

//nécessite spring-test dans pom.xml
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mySpringConf.xml")
public class TestServiceCompte {
	
	@Autowired
	private ServiceCompte serviceCompte ; //à tester
	
	
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
