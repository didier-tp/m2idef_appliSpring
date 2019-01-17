package com.m2i.tp.dao;

import java.util.List;
import java.util.Optional;

import com.m2i.tp.entity.Client;
import com.m2i.tp.entity.Compte;

public interface DaoClient {
	public	Optional<Client> findById(Long numero);

	public List<Client> findAll();

	// ...
	public void save(Client cli);

	public void deleteById(Long numero);

	public List<Compte> findComptesOfClient(Long numClient);
}
