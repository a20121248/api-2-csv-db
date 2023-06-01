package com.api2csv.demo.models.services;

import java.util.List;

import com.api2csv.demo.models.entity.Cliente;

public interface IClienteService {
	public List<Cliente> findAll();
}
