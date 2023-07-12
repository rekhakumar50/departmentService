package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	Department findByDepartmentCode(String departmentCode);

}
