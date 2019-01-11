package com.m2i.tp.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.m2i.tp.config.AppConfig;
import com.m2i.tp.entity.Compte;
//org.junit = Junit4
import com.m2i.tp.service.ServiceCompte;

//nécessite spring-test dans pom.xml
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/mySpringConf.xml")
@ContextConfiguration(classes= {AppConfig.class})
public class TestServiceCompte {
	
	@Autowired
	private ServiceCompte serviceCompte ; //à tester
	
	@Test
	public void testBonTransfert() {
		Compte cpt1 = new Compte(null,"compte 1",100.0);
		this.serviceCompte.saveOrUpdateCompte(cpt1);
		Compte cpt2 = new Compte(null,"compte 2",80.0);
		this.serviceCompte.saveOrUpdateCompte(cpt2);
		
		serviceCompte.transferer(50.0, 1L, 2L);
		
		Compte cpt1Apres = serviceCompte.rechercherCompteParNumero(1L);
		Compte cpt2Apres = serviceCompte.rechercherCompteParNumero(2L);
		System.out.println("apres transfert cpt1Apres="+cpt1Apres 
				          + " \n et cpt2Apres=" + cpt2Apres);
		Assert.assertEquals(50.0,cpt1Apres.getSolde(),0.001); //100 - 50 = 50
		Assert.assertEquals(130.0,cpt2Apres.getSolde(),0.001); //80 + 50 = 130
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
