package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Department;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public Department saveDepartment(DepartmentDto departmentDto) {
		Department department = null;
		try {
			department = departmentRepository.save(DepartmentMapper.convertToDepartment(departmentDto));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return department;
	}
	
	public List<DepartmentDto> getDepartments() {
		List<DepartmentDto> departmentDtos = new ArrayList<>();
		List<Department> departments = departmentRepository.findAll();
		
		if(CollectionUtils.isNotEmpty(departments)) {
			departmentDtos = departments.stream()
										.filter(Objects::nonNull)
										.map(depart -> DepartmentMapper.convertToDepartmentDto(depart))
										.filter(Objects::nonNull)
										.collect(Collectors.toList());
		}
		return departmentDtos;
	}
	
	public DepartmentDto getDepartmentByCode(String departmentCode) {
		DepartmentDto departmentDto = null;
		if(StringUtils.isNotEmpty(departmentCode)) {
			Department department = departmentRepository.findByDepartmentCode(departmentCode);
			departmentDto = DepartmentMapper.convertToDepartmentDto(department);
		}
		return departmentDto;
	}

}
