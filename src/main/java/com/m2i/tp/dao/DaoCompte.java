package com.m2i.tp.dao;

import java.util.List;
import java.util.Optional;

import com.m2i.tp.entity.Compte;
import com.m2i.tp.entity.Operation;

public interface DaoCompte {
	public	Optional<Compte> findById(Long numero);

	public List<Compte> findAll();
	public List<Operation> findOperationsOfCompte(Long numCpt);
    
	public void save(Compte cpt);

	public void deleteById(Long numero);
}
