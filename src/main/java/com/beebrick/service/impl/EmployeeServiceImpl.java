package com.beebrick.service.impl;

import java.util.List;
import java.util.Optional;

import com.beebrick.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.beebrick.repository.EmployeeRepository;
import com.beebrick.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAll() {
		return employeeRepository.getAll();
	}

	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> findByUsername(String username) {
		return employeeRepository.findById(username);
	}

	@Override
	public void delete(String username) {
		employeeRepository.delete(username);
	}
}

