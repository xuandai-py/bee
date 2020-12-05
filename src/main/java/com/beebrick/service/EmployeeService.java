package com.beebrick.service;

import java.util.List;
import java.util.Optional;

import com.beebrick.entity.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeService {

	List<Employee> getAll();

	void save(Employee employee);

	void delete(String username);

	Optional<Employee> findByUsername(String username);

//	Page<User> findPaginated(int pageNo, int pageSize);
//
//	Page<User> findPaginated1(String username, int pageNo, int pageSize);
//
//	List<User> findRoleInUser(Integer roleID);
}
