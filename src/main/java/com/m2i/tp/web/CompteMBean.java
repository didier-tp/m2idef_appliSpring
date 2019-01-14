package com.m2i.tp.web;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
*/

import com.m2i.tp.entity.Compte;
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
@Scope("session")
public class CompteMBean {
	
	@Inject //ou bien @Autowired
	private ServiceCompte serviceCompte;   //service métier "spring" vers lequel on va déléguer

	private Long numCpt;   //numero de compte à saisir
	private Compte compte; //compte remonté/recherché à afficher
	
	
	@PostConstruct //pour compenser base réinitialisée au démarrage en mode jpa "drop-and-create"
	public void initialiserJeuxDeDonneesEnModeDeveloppement() {
		//code idéalement délégué à sous composant utilitaire tenant compte d'un profile spring:
		serviceCompte.saveOrUpdateCompte(new Compte(null,"compte 1bis",100.0));
		serviceCompte.saveOrUpdateCompte(new Compte(null,"compte 2bis",200.0));
	}
	
	public String doSearchCompte() {
		this.compte = serviceCompte.rechercherCompteParNumero(this.numCpt);
		return null;//rester sur meme page/vue
	}

	public Long getNumCpt() {
		return numCpt;
	}

	public void setNumCpt(Long numCpt) {
		this.numCpt = numCpt;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public ServiceCompte getServiceCompte() {
		return serviceCompte;
	}

	public void setServiceCompte(ServiceCompte serviceCompte) {
		this.serviceCompte = serviceCompte;
	}
	
	
	

}
