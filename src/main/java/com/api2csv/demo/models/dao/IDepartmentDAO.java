package com.api2csv.demo.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.api2csv.demo.models.entity.Department;

public interface IDepartmentDAO extends CrudRepository<Department, Long>{

}
