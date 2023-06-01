package com.api2csv.demo.models.services;

import java.io.InputStream;
import java.util.List;

import com.api2csv.demo.models.entity.Department;

public interface IDepartmentService {
	public List<Department> findAll();
	public List<Department> saveAll(List<Department> departments);
	public void processCsv(InputStream file);
}
