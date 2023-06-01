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

import com.api2csv.demo.models.dao.IEmployeeDAO;
import com.api2csv.demo.models.entity.Department;
import com.api2csv.demo.models.entity.Employee;
import com.api2csv.demo.models.entity.Job;

import jakarta.transaction.Transactional;

@Service
public class IEmployeeServiceImpl implements IEmployeeService {
	private Logger logger = LoggerFactory.getLogger(IEmployeeServiceImpl.class);
	
	@Autowired
	private IEmployeeDAO employeeDao;
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		 return (List<Employee>) employeeDao.findAll();
	}
	
	@Override
	public List<Employee> saveAll(List<Employee> employees) {
		 return (List<Employee>) employeeDao.saveAll(employees);
	}
	
	public void processCsv(InputStream file) {
		logger.info("======================INICIANDO CARGA DE TABLA EMPLOYEES====================================");
	    try (
	    	BufferedReader fileReader = new BufferedReader(new InputStreamReader(file, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT);
	    ) {
	    	List<Employee> employees = new ArrayList<Employee>();
	    	for (CSVRecord csvRecord : csvParser) {
	    	     Long id = Long.parseLong(csvRecord.get(0));
	    	     String name = csvRecord.get(1);
	    	     String datetime = csvRecord.get(2);
	    	     String department_id_str = csvRecord.get(3);
	    	     String job_id_str = csvRecord.get(4);

	    	     if (department_id_str.equals("")) {
	    	    	 department_id_str = "0";
	    	     }
	    	     Long department_id = Long.parseLong(department_id_str);
	    	     Department department = new Department(department_id, "");
	    	     
	    	     if (job_id_str.equals("")) {
	    	    	 job_id_str = "0";
	    	     }
	    	     Long job_id = Long.parseLong(job_id_str);
	    	     Job job = new Job(job_id, "");
	    	     
	    	     if (department != null && job != null) {
		    	     Employee employee = new Employee(id, name, datetime, department, job);
		    	     employees.add(employee);
	    	     }
	    	}
	    	employeeDao.saveAll(employees);
	    	
	    } catch (IOException e) {
	    	throw new RuntimeException("Error en leer el CSV: " + e.getMessage());
	    }
        logger.info("======================FIN DE CARGA DE TABLA EMPLOYEES====================================");
	}
}
