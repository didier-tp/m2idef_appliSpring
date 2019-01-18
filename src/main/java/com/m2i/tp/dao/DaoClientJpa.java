package com.m2i.tp.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.entity.Client;
import com.m2i.tp.entity.Compte;

@Repository //@Repository = composant spring de type DAO
@Transactional //en version Spring (pour commit/rollback automatique)
public class DaoClientJpa implements DaoClient {
	
	@PersistenceContext //annotation standardisée de Java/JEE et JPA
	                    //qui sert à initialiser entityManager
						//en fonction META-INF/persistence.xml
	                    //ou d'une config équivalente spring
	private EntityManager entityManager;

	@Override
	public Optional<Client> findById(Long numero) {
		return Optional.ofNullable(entityManager.find(Client.class, numero));
		
	}

	@Override
	public List<Client> findAll() {
		return entityManager.createNamedQuery("Client.findAll",Client.class)
			                .getResultList();
	}

	@Override
	public void save(Client cli) {
		if(cli.getNumero()==null)
			entityManager.persist(cli);
		else
		    entityManager.merge(cli);
	}

	

	@Override
	public void deleteById(Long numero) {
		Client cli = entityManager.find(Client.class, numero);
        entityManager.remove(cli);
	}

	@Override
	public List<Compte> findComptesOfClient(Long numClient) {
		return entityManager.createNamedQuery("Client.findComptesOfClient",Compte.class)
				.setParameter("numCli", numClient)
                .getResultList();
	}

}
