package com.beebrick.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beebrick.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT * FROM products WHERE IsActive = 0", nativeQuery = true)
	public List<Product> getAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE products SET IsActive = 1 WHERE ProductID=?1", nativeQuery = true)
	void delete(Integer productID);

	@Query(value = "SELECT * FROM products WHERE CategoryID = 1 ORDER BY RAND() LIMIT 4", nativeQuery = true)
	public List<Product> getAllRamdom();
}
