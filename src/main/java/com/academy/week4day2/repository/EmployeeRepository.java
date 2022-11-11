package com.academy.week4day2.repository;

import com.academy.week4day2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findBySalaryGreaterThan(Double salary);

    List<Employee> findByAgeGreaterThan(int age);

    List<Employee> findByPosition(String position);

    Employee findTopByOrderBySalaryDesc();

}
