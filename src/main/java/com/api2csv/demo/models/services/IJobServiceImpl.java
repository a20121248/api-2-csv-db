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

import com.api2csv.demo.models.dao.IJobDAO;
import com.api2csv.demo.models.entity.Job;

import jakarta.transaction.Transactional;

@Service
public class IJobServiceImpl implements IJobService {
	private Logger logger = LoggerFactory.getLogger(IJobServiceImpl.class);
	
	@Autowired
	private IJobDAO jobDao;
	
	@Override
	@Transactional
	public List<Job> findAll() {
		 return (List<Job>) jobDao.findAll();
	}
	
	@Override
	@Transactional
	public List<Job> saveAll(List<Job> jobs) {
		 return (List<Job>) jobDao.saveAll(jobs);
	}
	
	public void processCsv(InputStream file) {
		logger.info("======================INICIANDO CARGA DE TABLA JOBS====================================");
	    try (
	    	BufferedReader fileReader = new BufferedReader(new InputStreamReader(file, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT);
	    ) {
	    	List<Job> jobs = new ArrayList<Job>();
	    	for (CSVRecord csvRecord : csvParser) {
	    	     Long id = Long.parseLong(csvRecord.get(0));
	    	     String name = csvRecord.get(1);
	    	     
	    	     Job job = new Job(id,name);
	    	     jobs.add(job);
	    	}
	    	jobDao.saveAll(jobs);
	    	
	    } catch (IOException e) {
	    	throw new RuntimeException("Error en leer el CSV: " + e.getMessage());
	    }
        logger.info("======================FIN DE CARGA DE TABLA JOBS====================================");
	}
}
