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
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestServiceCompte {
	
	@Autowired
	private ServiceCompte serviceCompte ; //à tester
	
	@Test
	public void test1_BonTransfert() {
		Compte cptA = new Compte(null,"compte A",100.0);
		this.serviceCompte.saveOrUpdateCompte(cptA);
		Long numCptA = cptA.getNumero();
		Compte cptB = new Compte(null,"compte B",80.0);
		this.serviceCompte.saveOrUpdateCompte(cptB);
		Long numCptB = cptB.getNumero();
		serviceCompte.transferer(50.0, numCptA, numCptB);
		Compte cptAApres = serviceCompte.rechercherCompteParNumero(numCptA);
		Compte cptBApres = serviceCompte.rechercherCompteParNumero(numCptB);
		System.out.println("apres transfert cptAApres="+cptAApres 
				          + " \n et cptBApres=" + cptBApres);
		Assert.assertEquals(50.0,cptAApres.getSolde(),0.001); //100 - 50 = 50
		Assert.assertEquals(130.0,cptBApres.getSolde(),0.001); //80 + 50 = 130
	}
	
	@Test
	public void test2_AjoutCompte() {
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
