package com.beebrick.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beebrick.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	@Query(value = "SELECT * FROM roles WHERE IsActive = 0", nativeQuery = true)
	public Page<Role> getAllRole(Pageable pageable);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE roles SET IsActive = 1 WHERE RoleID=?1", nativeQuery = true)
	void delete(Integer roleID);
	
}
