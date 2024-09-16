package com.javatest.javabackend.repository;

import com.javatest.javabackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee, Long> {
}
