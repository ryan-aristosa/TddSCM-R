package com.academy.week4day2.service;

import com.academy.week4day2.model.Employee;
import com.academy.week4day2.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    private Employee DONDON, MICHAEL, R, ALI, RAVEN;
    private List<Employee> employeeList;


    @BeforeEach
    public void setup() {
        DONDON = new Employee("Dondon", 26, 200000d, "Senior Software Engineer");
        MICHAEL = new Employee("Michael", 31, 150000d, "Jr. Software Engineer");
        R = new Employee("R", 20, 100000d, "Jr. Software Engineer");
        ALI = new Employee("Ali", 25, 20000d, "Jr. Software Engineer");
        RAVEN = new Employee("Raven", 22, 70000d, "Jr. Software Engineer");
        employeeList = List.of(DONDON, MICHAEL, R, ALI, RAVEN);
    }

    @Test
    @DisplayName("GIVEN employees " +
            "WHEN findEmployeesBySalaryGreaterThan() is executed with input of salary " +
            "THEN result should return employees whose salary are greater than it")
    public void testEmployeesBySalaryGreaterThan() {
        // Arrange
        double salary = 90000d;
        Mockito.when(employeeRepository.findBySalaryGreaterThan(salary))
                .thenReturn(employeeList.stream().filter(employee -> employee.getSalary() > salary).toList());
        // Act
        List<Employee> actualList = employeeServiceImpl.findEmployeesBySalaryGreaterThan(salary);
        // Assert
        assertThat(actualList).containsExactly(DONDON, MICHAEL, R);
    }

    @Test
    @DisplayName("GIVEN employees " +
            "WHEN findEmployeesByAgeGreaterThan() is executed with input of age " +
            "THEN result should return employees whose age are greater than it")
    public void testEmployeesByAgeGreaterThan() {
        // Arrange
        int age = 23;
        Mockito.when(employeeRepository.findByAgeGreaterThan(age))
                .thenReturn(employeeList.stream().filter(employee -> employee.getAge() > age).toList());
        // Act
        List<Employee> actualList = employeeServiceImpl.findEmployeesByAgeGreaterThan(age);
        // Assert
        assertThat(actualList).containsExactly(DONDON, MICHAEL, ALI);
    }

    @Test
    @DisplayName("GIVEN employees " +
            "WHEN findEmployeesByPosition() is executed with input of position " +
            "THEN result should return employees whose position matches")
    public void testEmployeesByPosition() {
        // Arrange
        String position = "Jr. Software Engineer";
        Mockito.when(employeeRepository.findByPosition(position))
                .thenReturn(employeeList.stream().filter(employee -> employee.getPosition().equals(position)).toList());
        // Act
        List<Employee> actualList = employeeServiceImpl.findEmployeesByPosition(position);
        // Assert
        assertThat(actualList).containsExactly(MICHAEL, R, ALI, RAVEN);
    }

    @Test
    @DisplayName("GIVEN employees " +
            "WHEN findEmployeeTopBySalary() is executed " +
            "THEN result should return employee who have the highest salary")
    public void testEmployeeTopBySalary() {
        // Arrange
        Mockito.when(employeeRepository.findTopByOrderBySalaryDesc())
                .thenReturn(employeeList.stream().max(Comparator.comparing(Employee::getSalary)).get());
        // Act
        Employee actualEmployee = employeeServiceImpl.findEmployeeTopBySalary();
        // Assert
        assertEquals(DONDON, actualEmployee);
    }

}