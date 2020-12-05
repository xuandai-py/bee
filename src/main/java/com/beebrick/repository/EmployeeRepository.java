package com.beebrick.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beebrick.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	@Query(value = "SELECT * FROM employees WHERE IsActive = 0", nativeQuery = true)
	public List<Employee> getAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE employees SET IsActive = 1 WHERE Username=?1", nativeQuery = true)
	void delete(String username);

//	@Query(value = "SELECT * FROM users WHERE Username LIKE %?1% and IsActive = 0", nativeQuery = true)
//	public Page<User> searchUser(String username, Pageable pageable);
//
//	@Query(value = "SELECT * FROM users WHERE Username LIKE %?1% and IsActive = 0", nativeQuery = true)
//	public List<User> search1(String pageable);
//
//	@Query(value = "SELECT * FROM users WHERE users.RoleID = ?1", nativeQuery = true)
//	public List<User> findRoleInUser(Integer roleID);
}
