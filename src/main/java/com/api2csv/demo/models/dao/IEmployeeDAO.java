package com.api2csv.demo.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.api2csv.demo.models.entity.Employee;

public interface IEmployeeDAO extends CrudRepository<Employee, Long> {

}
