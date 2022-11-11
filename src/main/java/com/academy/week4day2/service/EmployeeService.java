package com.academy.week4day2.service;

import com.academy.week4day2.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findEmployeesBySalaryGreaterThan(Double salary);

    List<Employee> findEmployeesByAgeGreaterThan(int age);

    List<Employee> findEmployeesByPosition(String position);

    Employee findEmployeeTopBySalary();

}
