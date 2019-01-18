package com.m2i.tp.dao;

import java.util.List;
import java.util.Optional;

import com.m2i.tp.entity.Client;
import com.m2i.tp.entity.InfoAuth;

public interface DaoInfoAuth {
	public	Optional<InfoAuth> findById(String username);

	public List<InfoAuth> findAll();

	// ...
	public void save(InfoAuth ia);

	public void deleteById(String username);
	
	public Client clientFromUsername(String username);

}
