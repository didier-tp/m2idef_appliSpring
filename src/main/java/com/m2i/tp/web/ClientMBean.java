package com.m2i.tp.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
*/

import com.m2i.tp.entity.Client;
import com.m2i.tp.entity.Compte;
import com.m2i.tp.service.ServiceClient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Named //ou bien @Component
//NB: @Component n'est pas bien interprété par assistant eclipse --> pas autocompletion du cote .xhtml
//    @Named est bien interprété partout (Spring , JSF , eclipse , ....) mais
//    nécessite javax.inject dans pom.xml
@Scope("session")
@Getter @Setter @NoArgsConstructor
public class ClientMBean {
	
	@Inject //ou bien @Autowired
	private ServiceClient serviceClient;   //service métier "spring" vers lequel on va déléguer
	
	@Inject //ou bien @Autowired
	private InitDataMBean initDataMBean; // pour initialiser jeux de données en mode developpement

	private String username;
	private String password;
	
	private Long numClient;   //numero de client à saisir (ou bien calculé )
	private Client client; //client à afficher
	private List<Compte> comptesDuClient; //comptes remonté/recherché à afficher
	private String message;
	
	@PostConstruct
	public void init() {
		//temporairement avant préliminaire login.xhtml
		numClient=1L;
		doSearchClientWithComptes();
		message="acces direct client 1 sans login";
	}
	
	public String doSearchClientWithComptesFromLogin() {
		this.client = serviceClient.clientFromVerifInfoAuth(username, password);
		if(client==null) {
			this.message="wrong username or password";
			return null;
		}
		else {
			this.numClient = client.getNumero();
			actualiserListeComptes();
			message="client authentifié";
		}
		return "espaceClient";//naviguer si login ok
	}
	
	public String doSearchClientWithComptes() {
		this.client = serviceClient.rechercherClientParNumero(this.numClient);
		actualiserListeComptes();
		message="";
		return null;//rester sur meme page/vue
	}
	
	public void actualiserListeComptes() {
		this.comptesDuClient = serviceClient.rechercherComptesDuClient(numClient);
	}

}
