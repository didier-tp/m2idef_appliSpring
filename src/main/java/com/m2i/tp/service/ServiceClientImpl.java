package com.m2i.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.dao.DaoClient;
import com.m2i.tp.dao.DaoCompte;
import com.m2i.tp.entity.Client;
import com.m2i.tp.entity.Compte;


@Service //héritant de @Component
@Transactional
public class ServiceClientImpl implements ServiceClient {
	
	
	@Autowired
	private DaoClient daoClient; //dao vers lequel déléguer
	
	@Autowired
	private DaoCompte daoCompte; //dao vers lequel déléguer
	

	@Override
	public Client rechercherClientParNumero(Long numero) {
		return daoClient.findById(numero).orElse(null);
	}

	@Override
	public void saveOrUpdateClient(Client cpt) {
			daoClient.save(cpt);
	}

	@Override
	public void supprimerClient(Long numClient) {
		daoClient.deleteById(numClient);
	}

	@Override
	public List<Compte> rechercherComptesDuClient(Long numClient) {
		return daoClient.findComptesOfClient(numClient);
	}

	@Override
	public void ajouterComptePourClient(Long numClient, Long numCompte) {
		Client client = daoClient.findById(numClient).get();
		Compte compte = daoCompte.findById(numCompte).get();
		// client.getComptes().add(compte); //ne suffit pas car lien du coté secondaire ("mappedBy")
		compte.getProprietaires().add(client);
		//save automatique à l'état persistant (contexte @Transactional)
	}

	
}
