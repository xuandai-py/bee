package com.beebrick.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beebrick.entity.Blog;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{

	@Query(value = "SELECT * FROM blogs WHERE IsActive = 0", nativeQuery = true)
	public List<Blog> getAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE blogs SET IsActive = 1 WHERE BlogID=?1", nativeQuery = true)
	void delete(Integer blogID);
}
