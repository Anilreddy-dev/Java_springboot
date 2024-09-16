package com.javatest.javabackend.controller;

import com.javatest.javabackend.exception.ResourceNotfound;
import com.javatest.javabackend.model.Employee;
import com.javatest.javabackend.repository.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRespository employeeRespository;

    //get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRespository.findAll();
    }

    //insert api
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRespository.save(employee);

    }

    //get by id
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRespository.findById(id).orElseThrow(() -> new ResourceNotfound("Employee not found with id " + id));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody  Employee employeeDetails) {
        Employee updateEmployee = employeeRespository.findById(id).orElseThrow(() -> new ResourceNotfound("Employee not found with id " + id));
        updateEmployee.setFirstname(employeeDetails.getFirstname());
        updateEmployee.setLastname(employeeDetails.getLastname());
        updateEmployee.setEmail(employeeDetails.getEmail());
        updateEmployee.setPhone(employeeDetails.getPhone());
        employeeRespository.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRespository.findById(id).orElseThrow(() -> new ResourceNotfound("Employee not found with id " + id));
        employeeRespository.delete(employee);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
