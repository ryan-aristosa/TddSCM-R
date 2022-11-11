package com.academy.week4day2.controller;

import com.academy.week4day2.model.Employee;
import com.academy.week4day2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/salary")
    public ResponseEntity<List<Employee>> getEmployeesBySalaryGreaterThan(@RequestParam Double salary) {
        List<Employee> employees = employeeService.findEmployeesBySalaryGreaterThan(salary);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/age")
    public ResponseEntity<List<Employee>> getEmployeesByAgeGreaterThan(@RequestParam int age) {
        List<Employee> employees = employeeService.findEmployeesByAgeGreaterThan(age);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/position")
    public ResponseEntity<List<Employee>> getEmployeesByPosition(@RequestParam String position) {
        List<Employee> employees = employeeService.findEmployeesByPosition(position);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/salary/top")
    public ResponseEntity<Employee> getEmployeeTopBySalary() {
        Employee employee = employeeService.findEmployeeTopBySalary();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }


}
