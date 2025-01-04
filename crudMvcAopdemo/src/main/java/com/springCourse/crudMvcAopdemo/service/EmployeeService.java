package com.springCourse.crudMvcAopdemo.service;

import com.springCourse.crudMvcAopdemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    void save(Employee theEmployee);

    void deleteById(int theId);

}
