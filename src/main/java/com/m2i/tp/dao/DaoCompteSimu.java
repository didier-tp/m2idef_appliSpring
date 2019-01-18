package com.m2i.tp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.m2i.tp.entity.Compte;
import com.m2i.tp.entity.Operation;

//@Repository //ou bien @Component
//id par défaut = "daoCompteSimu" (nom de la classe avec minuscule au début).
public class DaoCompteSimu implements DaoCompte {

	private Map<Long, Compte> mapComptes = new HashMap<Long, Compte>();
	private Long nbComptes = 0L;

	@Override
	public Optional<Compte> findById(Long numero) {
		return Optional.of(mapComptes.get(numero));
	}


	private void createCompte(Compte cpt) {
		// en entrée: cpt avec numero = null
		nbComptes++;// simuler auto_increment
		cpt.setNumero(nbComptes);
		mapComptes.put(cpt.getNumero(), cpt);
	}

	@Override
	public List<Compte> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Compte cpt) {
		if(cpt.getNumero()==null)
			createCompte(cpt);
	}

	@Override
	public void deleteById(Long numero) {
		// TODO Auto-generated method stub

	}


	@Override
	public List<Operation> findOperationsOfCompte(Long numCpt) {
		// TODO Auto-generated method stub
		return null;
	}

}
