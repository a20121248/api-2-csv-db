package com.api2csv.demo.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api2csv.demo.models.dao.IClienteDAO;
import com.api2csv.demo.models.entity.Cliente;

import jakarta.transaction.Transactional;

@Service
public class IClienteServiceImpl implements IClienteService {
	@Autowired
	private IClienteDAO clienteDao;
	
	@Override
	@Transactional
	public List<Cliente> findAll() {
		 return (List<Cliente>) clienteDao.findAll();
	}
}
