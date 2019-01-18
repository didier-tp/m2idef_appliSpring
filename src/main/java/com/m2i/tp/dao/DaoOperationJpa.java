package com.m2i.tp.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.entity.Operation;

@Repository //@Repository = composant spring de type DAO
@Transactional //en version Spring (pour commit/rollback automatique)
public class DaoOperationJpa implements DaoOperation {
	
	@PersistenceContext //annotation standardisée de Java/JEE et JPA
	                    //qui sert à initialiser entityManager
						//en fonction META-INF/persistence.xml
	                    //ou d'une config équivalente spring
	private EntityManager entityManager;

	@Override
	public Optional<Operation> findById(Long numero) {
		return Optional.of(entityManager.find(Operation.class, numero));
	}

	@Override
	public List<Operation> findAll() {
		return entityManager.createQuery("SELECT c FROM Operation c",Operation.class)
			                .getResultList();
	}

	@Override
	public void save(Operation op) {
		if(op.getNumOp()==null)
			entityManager.persist(op);
		else
		    entityManager.merge(op);
	}

	

	@Override
	public void deleteById(Long numero) {
		Operation op = entityManager.find(Operation.class, numero);
        entityManager.remove(op);
	}

}
