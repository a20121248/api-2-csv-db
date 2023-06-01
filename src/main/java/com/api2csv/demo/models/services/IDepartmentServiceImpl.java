package com.api2csv.demo.models.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api2csv.demo.models.dao.IDepartmentDAO;
import com.api2csv.demo.models.entity.Department;

import jakarta.transaction.Transactional;

@Service
public class IDepartmentServiceImpl implements IDepartmentService {
	private Logger logger = LoggerFactory.getLogger(IDepartmentServiceImpl.class);
	
	@Autowired
	private IDepartmentDAO departmentDao;
	
	@Override
	@Transactional
	public List<Department> findAll() {
		 return (List<Department>) departmentDao.findAll();
	}

	@Override
	@Transactional
	public List<Department> saveAll(List<Department> departments) {
		 return (List<Department>) departmentDao.saveAll(departments);
	}
	
	public void processCsv(InputStream file) {
		logger.info("======================INICIANDO CARGA DE TABLA DEPARTMENTS====================================");
	    try (
	    	BufferedReader fileReader = new BufferedReader(new InputStreamReader(file, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT);
	    ) {
	    	List<Department> departments = new ArrayList<Department>();
	    	for (CSVRecord csvRecord : csvParser) {
	    	     Long id = Long.parseLong(csvRecord.get(0));
	    	     String name = csvRecord.get(1);
	    	     
	    	     Department department = new Department(id,name);
	    	     departments.add(department);
	    	}
	    	departmentDao.saveAll(departments);
	    	
	    } catch (IOException e) {
	    	throw new RuntimeException("Error en leer el CSV: " + e.getMessage());
	    }
        logger.info("======================FIN DE CARGA DE TABLA DEPARMENTS====================================");
	}
}
