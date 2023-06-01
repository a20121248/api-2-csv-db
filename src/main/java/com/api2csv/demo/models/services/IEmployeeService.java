package com.api2csv.demo.models.services;

import java.io.InputStream;
import java.util.List;

import com.api2csv.demo.models.entity.Employee;

public interface IEmployeeService {
	public List<Employee> findAll();
	public List<Employee> saveAll(List<Employee> employees);
	public void processCsv(InputStream file);
}
