package com.academy.week4day2.controller;

import com.academy.week4day2.model.Employee;
import com.academy.week4day2.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

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
    @DisplayName("Result should validate that employeeList is not empty")
    public void testEmployeeIsNotEmpty() {
        assertThat(employeeList).isNotEmpty();
    }

    @Test
    @DisplayName("GIVEN a get request " +
            "WHEN getEmployeesBySalaryGreaterThan() is executed with input of salary " +
            "THEN result should return employees whose salary are greater than it")
    public void testEmployeesBySalaryGreaterThan() throws Exception {
        double salary = 90000d;
        Mockito.when(employeeService.findEmployeesBySalaryGreaterThan(salary))
                .thenReturn(employeeList.stream().filter(employee -> employee.getSalary() > salary).toList());

        List<Employee> expectedList = List.of(DONDON, MICHAEL, R);
        String expectedJSONString = JSONArray.toJSONString(expectedList);
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/salary?salary=" + salary)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJSONString));
    }

    @Test
    @DisplayName("GIVEN a get request " +
            "WHEN getEmployeesByAgeGreaterThan() is executed with input of age " +
            "THEN result should return employees whose age are greater than it")
    public void testEmployeesByAgeGreaterThan() throws Exception {
        int age = 23;
        Mockito.when(employeeService.findEmployeesByAgeGreaterThan(age))
                .thenReturn(employeeList.stream().filter(employee -> employee.getAge() > age).toList());

        List<Employee> expectedList = List.of(DONDON, MICHAEL, ALI);
        String expectedJSONString = JSONArray.toJSONString(expectedList);
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/age?age=" + age)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJSONString));
    }

    @Test
    @DisplayName("GIVEN a get request " +
            "WHEN getEmployeesByPosition() is executed with input of position " +
            "THEN result should return employees whose position matches")
    public void testEmployeesByPosition() throws Exception {
        String position = "Jr. Software Engineer";
        Mockito.when(employeeService.findEmployeesByPosition(position))
                .thenReturn(employeeList.stream().filter(employee -> employee.getPosition().equals(position)).toList());

        List<Employee> expectedList = List.of(MICHAEL, R, ALI, RAVEN);
        String expectedJSONString = JSONArray.toJSONString(expectedList);
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/position?position=" + position)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJSONString));
    }

    @Test
    @DisplayName("GIVEN a get request " +
            "WHEN getEmployeeTopBySalary() is executed " +
            "THEN result should return employee who have the highest salary")
    public void testEmployeeTopBySalary() throws Exception {
        Mockito.when(employeeService.findEmployeeTopBySalary())
                .thenReturn(employeeList.stream().max(Comparator.comparing(Employee::getSalary)).get());

        ObjectMapper mapper = new ObjectMapper();
        String expectedJSONString = mapper.writeValueAsString(DONDON);
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/salary/top")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJSONString));
    }

}
