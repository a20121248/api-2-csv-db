package com.api2csv.demo.models.services;

import java.io.InputStream;
import java.util.List;

import com.api2csv.demo.models.entity.Job;

public interface IJobService {
	public List<Job> findAll();
	public List<Job> saveAll(List<Job> jobs);
	public void processCsv(InputStream file);
}
