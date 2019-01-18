package com.m2i.tp.dao;

import java.util.List;
import java.util.Optional;

import com.m2i.tp.entity.Operation;

public interface DaoOperation {
	public	Optional<Operation> findById(Long numero);

	public List<Operation> findAll();

	// ...
	public void save(Operation op);

	public void deleteById(Long numero);
}
