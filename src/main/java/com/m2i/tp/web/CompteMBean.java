package com.m2i.tp.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.m2i.tp.entity.Compte;
import com.m2i.tp.service.ServiceCompte;

@ManagedBean
@SessionScoped
public class CompteMBean {
	
	private Long numCpt;
	private Compte compte;
	
	@ManagedProperty("#{serviceCompteImpl}")
	private ServiceCompte serviceCompte;
	
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
