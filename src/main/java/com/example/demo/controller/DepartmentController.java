package com.example.demo.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.Department;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping
	public ResponseEntity<String> saveDepartment(@RequestBody DepartmentDto departmentDto) {
		try {
			Department department = departmentService.saveDepartment(departmentDto);
			if(Objects.nonNull(department)) {
				return new ResponseEntity<String>("Department Data Saved!!", HttpStatus.CREATED);
			}
			return new ResponseEntity<String>("Department Data Not Saved!!", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(Exception e) {
			return new ResponseEntity<String>("Department Data Not Saved!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getDepartments() {
		List<DepartmentDto> departmentDtos = departmentService.getDepartments();
		return new ResponseEntity<List<DepartmentDto>>(departmentDtos, HttpStatus.OK);
	}
	
	@GetMapping("/{departmentCode}")
	public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable String departmentCode) {
		DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
		return new ResponseEntity<DepartmentDto>(departmentDto, HttpStatus.OK);
	}

}
