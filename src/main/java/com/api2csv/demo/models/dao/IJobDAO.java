package com.api2csv.demo.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.api2csv.demo.models.entity.Job;

public interface IJobDAO extends CrudRepository<Job, Long> {

}
