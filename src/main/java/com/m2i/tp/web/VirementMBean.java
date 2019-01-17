package com.m2i.tp.web;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
*/

import com.m2i.tp.service.ServiceCompte;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Named //ou bien @Component
//NB: @Component n'est pas bien interprété par assistant eclipse --> pas autocompletion du cote .xhtml
//    @Named est bien interprété partout (Spring , JSF , eclipse , ....) mais
//    nécessite javax.inject dans pom.xml
@Scope("request")
@Getter @Setter @NoArgsConstructor
public class VirementMBean {
	
	private Long numCptDeb;
	private Long numCptCred;
	private Double montant;
	
	@Inject //ou bien @Autowired
	private ServiceCompte serviceComptes;   //service métier "spring" vers lequel on va déléguer
	
	@Inject //ou bien @Autowired
	private ClientMBean clientMBean; // pour que VirementMBean puisse accéder à ClientMBean

	private String message;
	
	public String effectuerVirement(){
		String suite=null;
		try {
			//effectuer le virement
			System.out.println("virement de montant="+montant + " de numCptDeb=" + numCptDeb + " vers numCptCred=" + numCptCred);
			serviceComptes.transferer(montant, numCptDeb, numCptCred);
		    //reactualiser les soldes:
			clientMBean.actualiserListeComptes();
			//message:
			clientMBean.setMessage("virement bien effectué : " +montant +" du compte " + numCptDeb +  " vers le compte " + numCptCred);
			suite="espaceClient"; //.jsp ou .xhtml	
		} catch (Exception e) {
			clientMBean.setMessage("echec identification - " + e.getMessage());
		}
		return suite;
	}
	


}
