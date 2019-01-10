package com.m2i.tp.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.tp.dao.DaoCompte;
import com.m2i.tp.entity.Compte;

@Service //héritant de @Component
public class ServiceCompteImpl implements ServiceCompte {
	
	//NB: daoCompte pourra référencer une instance 
	//de type DaoCompteSimu ou bien DaoCompteJpa
	
	public ServiceCompteImpl() {
		System.out.println("dans constructeur daoCompte="+daoCompte);
	}
	
	@PostConstruct
	public void init() {
		System.out.println("dans init prefixé par @PostConstruct daoCompte="
	                       +daoCompte);
	}
	
	private DaoCompte daoCompte; //dao vers lequel déléguer
	
	//méthode d'injection de dépendance
	@Autowired //ici ou bien directement au dessus du private
	//@Autowired de Spring ressemble à @EJB ou @Inject de DI/CDI
	//et demande à injecter un composant spring existant compatible avec l'interface DaoCompte
	public void setDaoCompte(DaoCompte daoCompte) {
		this.daoCompte = daoCompte;
	}

	@Override
	public Compte rechercherCompteParNumero(Long numero) {
		return daoCompte.findCompteByNumero(numero);
	}

	@Override
	public void saveOrUpdateCompte(Compte cpt) {
		if(cpt.getNumero()==null) {
			daoCompte.createCompte(cpt);
		}else 
			daoCompte.updateCompte(cpt);
	}

	@Override
	public void transferer(Double montant, Long numCptDeb, Long numCptCred) {
		// sera codé plus tard

	}

}
