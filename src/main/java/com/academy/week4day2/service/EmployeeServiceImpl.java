package com.academy.week4day2.service;

import com.academy.week4day2.model.Employee;
import com.academy.week4day2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> findEmployeesBySalaryGreaterThan(Double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    @Override
    public List<Employee> findEmployeesByAgeGreaterThan(int age) {
        return employeeRepository.findByAgeGreaterThan(age);
    }

    @Override
    public List<Employee> findEmployeesByPosition(String position) {
        return employeeRepository.findByPosition(position);
    }

    @Override
    public Employee findEmployeeTopBySalary() {
        return employeeRepository.findTopByOrderBySalaryDesc();
    }

}
