package com.m2i.tp.web;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
*/

import com.m2i.tp.entity.Adresse;
import com.m2i.tp.entity.Client;
import com.m2i.tp.entity.Compte;
import com.m2i.tp.entity.Operation;
import com.m2i.tp.service.ServiceClient;
import com.m2i.tp.service.ServiceCompte;

/*
//VERSION AVEC ANNOTATIONS DE JSF
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class CompteMBean {
	
	@ManagedProperty("#{serviceCompteImpl}")
	private ServiceCompte serviceCompte;   //service métier "spring" vers lequel on va déléguer
	*/

@Named //ou bien @Component
//NB: @Component n'est pas bien interprété par assistant eclipse --> pas autocompletion du cote .xhtml
//    @Named est bien interprété partout (Spring , JSF , eclipse , ....) mais
//    nécessite javax.inject dans pom.xml
@Scope("application")
public class InitDataMBean {
	
	@Inject //ou bien @Autowired
	private ServiceCompte serviceCompte;   //service métier "spring" vers lequel on va déléguer
	@Inject //ou bien @Autowired
	private ServiceClient serviceClient;   //service métier "spring" vers lequel on va déléguer

		
	@PostConstruct //pour compenser base réinitialisée au démarrage en mode jpa "drop-and-create"
	public void initialiserJeuxDeDonneesEnModeDeveloppement() {
		Client cli = new Client(null,"Therieur","alain","0102030405" ,"alain.therieur@ici_ou_la.fr");
		   cli.setAdresse(new Adresse("12, rue Elle " , "75000" , "Paris"));
		serviceClient.saveOrUpdateClient(cli);
		serviceClient.setInfoAuth(cli.getNumero(), "alain.therieur", "pwd1");
		
		Client autreCli = new Client(null,"Therieur","alex","0504030201" ,"alex.therieur@ici_ou_la.fr");
		  autreCli.setAdresse(new Adresse("25, rue des pas perdus " , "76000" , "Rouen"));
		serviceClient.saveOrUpdateClient(autreCli);
		serviceClient.setInfoAuth(autreCli.getNumero(), "alex.therieur", "pwd2");
		
		Compte cptA = new Compte(null,"compte A",100.0);
		this.serviceCompte.saveOrUpdateCompte(cptA);
		serviceCompte.addOperation(cptA.getNumero(),new Operation("achat 1" , -30.60));
		serviceCompte.addOperation(cptA.getNumero(),new Operation("achat 2" , -31.60));
		serviceCompte.addOperation(cptA.getNumero(),new Operation("payement salaire" , 2000.60));
		
		Compte cptB = new Compte(null,"compte B",80.0);
		this.serviceCompte.saveOrUpdateCompte(cptB);
		serviceCompte.addOperation(cptB.getNumero(),new Operation("achat x" , -60.60));
		serviceCompte.addOperation(cptB.getNumero(),new Operation("achat y" , -81.60));
		serviceCompte.addOperation(cptB.getNumero(),new Operation("payement salaire" , 2500.60));
		
		Compte cptC = new Compte(null,"compte C",60.0);
		this.serviceCompte.saveOrUpdateCompte(cptC);
		
		Compte cptD = new Compte(null,"compte D",60.0);
		this.serviceCompte.saveOrUpdateCompte(cptD);
		
		serviceClient.ajouterComptePourClient(cli.getNumero(), cptA.getNumero());
		serviceClient.ajouterComptePourClient(cli.getNumero(), cptB.getNumero());
		
		serviceClient.ajouterComptePourClient(autreCli.getNumero(), cptC.getNumero());
		serviceClient.ajouterComptePourClient(autreCli.getNumero(), cptD.getNumero());
	}
	

}
