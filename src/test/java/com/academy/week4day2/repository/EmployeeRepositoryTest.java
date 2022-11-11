package com.academy.week4day2.repository;

import com.academy.week4day2.model.Employee;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    @DisplayName("GIVEN employees from SQL " +
            "WHEN findAll() is executed " +
            "THEN result should validate that repository is not empty")
    public void testRepositoryIsNotEmpty() {
        // Arrange
        // Act
        List<Employee> employeeList = employeeRepository.findAll();
        // Assert
        assertThat(employeeList).isNotEmpty();
    }

    @Test
    @DisplayName("GIVEN employees from SQL " +
            "WHEN findBySalaryGreaterThan() is executed with input of salary " +
            "THEN result should return employees whose salary are greater than it")
    public void testBySalaryGreaterThan() {
        // Arrange
        List<Employee> employeeList = employeeRepository.findAll();
        Employee dondon = employeeList.get(0);
        Employee michael = employeeList.get(1);
        Employee r = employeeList.get(2);
        double salary = 90000d;

        // Act
        List<Employee> actualList = employeeRepository.findBySalaryGreaterThan(salary);

        // Assert
        assertThat(actualList).containsExactly(dondon, michael, r);
    }

    @Test
    @DisplayName("GIVEN employees from SQL " +
            "WHEN findByAgeGreaterThan() is executed with input of age " +
            "THEN result should return employees whose age are greater than it")
    public void testByAgeGreaterThan() {
        // Arrange
        List<Employee> employeeList = employeeRepository.findAll();
        Employee dondon = employeeList.get(0);
        Employee michael = employeeList.get(1);
        Employee ali = employeeList.get(3);
        int age = 23;

        // Act
        List<Employee> actualList = employeeRepository.findByAgeGreaterThan(age);

        // Assert
        assertThat(actualList).containsExactly(dondon, michael, ali);
    }

    @Test
    @DisplayName("GIVEN employees from SQL " +
            "WHEN findByPosition() is executed with input of position " +
            "THEN result should return employees whose position matches")
    public void testByPosition() {
        // Arrange
        List<Employee> employeeList = employeeRepository.findAll();
        Employee michael = employeeList.get(1);
        Employee r = employeeList.get(2);
        Employee ali = employeeList.get(3);
        Employee raven = employeeList.get(4);
        String position = "Jr. Software Engineer";

        // Act
        List<Employee> actualList = employeeRepository.findByPosition(position);

        // Assert
        assertThat(actualList).containsExactly(michael, r, ali, raven);
    }

    @Test
    @DisplayName("GIVEN employees from SQL " +
            "WHEN findTopByOrderBySalaryDesc() is executed " +
            "THEN result should return an employee who have the highest salary")
    public void testTopByOrderBySalaryDesc() {
        // Arrange
        List<Employee> employeeList = employeeRepository.findAll();
        Employee dondon = employeeList.get(0);
        // Act
        Employee actualEmployee = employeeRepository.findTopByOrderBySalaryDesc();
        // Assert
        assertEquals(dondon, actualEmployee);
    }

}
