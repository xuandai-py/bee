package com.beebrick.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beebrick.entity.Manufacturer;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>{

	@Query(value = "SELECT * FROM manufactures WHERE IsActive = 0", nativeQuery = true)
	public List<Manufacturer> getAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE manufactures SET IsActive = 1 WHERE ManufacturerID=?1", nativeQuery = true)
	void delete(Integer manufacturerID);

	@Query(value = "SELECT * FROM manufactures WHERE ManufacturerName = ?1", nativeQuery = true)
	public List<Manufacturer> findByName(String manufacturerName);
}
