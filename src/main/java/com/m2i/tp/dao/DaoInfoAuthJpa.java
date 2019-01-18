package com.m2i.tp.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.entity.Client;
import com.m2i.tp.entity.InfoAuth;

@Repository //@Repository = composant spring de type DAO
@Transactional //en version Spring (pour commit/rollback automatique)
public class DaoInfoAuthJpa implements DaoInfoAuth {
	
	@PersistenceContext //annotation standardisée de Java/JEE et JPA
	                    //qui sert à initialiser entityManager
						//en fonction META-INF/persistence.xml
	                    //ou d'une config équivalente spring
	private EntityManager entityManager;

	@Override
	public Optional<InfoAuth> findById(String username) {
		return Optional.ofNullable(entityManager.find(InfoAuth.class, username));
		
	}

	@Override
	public List<InfoAuth> findAll() {
		return entityManager.createNamedQuery("InfoAuth.findAll",InfoAuth.class)
			                .getResultList();
	}

	@Override
	public void save(InfoAuth ia) {
			entityManager.persist(ia);
	}

	

	@Override
	public void deleteById(String username) {
		InfoAuth ia = entityManager.find(InfoAuth.class, username);
        entityManager.remove(ia);
	}

	@Override
	public Client clientFromUsername(String username) {
		return entityManager.createNamedQuery("InfoAuth.clientFromUsername", Client.class)
				.setParameter("username", username)
				.getSingleResult();
	}


}
