package com.api2csv.demo.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.api2csv.demo.models.entity.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Long>{
	
}
