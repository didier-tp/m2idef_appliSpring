package com.m2i.tp.service;

import com.m2i.tp.dao.DaoCompte;
import com.m2i.tp.entity.Compte;

public class ServiceCompteImpl implements ServiceCompte {
	
	//NB: daoCompte pourra référence une instance 
	//de type DaoCompteSimu ou bien DaoCompteJpa
	
	private DaoCompte daoCompte; //dao vers lequel déléguer
	
	//méthode d'injection de dépendance
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
