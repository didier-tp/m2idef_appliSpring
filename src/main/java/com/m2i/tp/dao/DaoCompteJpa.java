package com.m2i.tp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.m2i.tp.entity.Compte;

//@Repository
public class DaoCompteJpa implements DaoCompte {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Compte findCompteByNumero(Long numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> findAllComptes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCompte(Compte cpt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createCompte(Compte cpt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCompte(Long numero) {
		// TODO Auto-generated method stub

	}

}
