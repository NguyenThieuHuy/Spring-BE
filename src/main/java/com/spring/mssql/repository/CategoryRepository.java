package com.spring.mssql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mssql.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Category findBycategoryid(int categoryid);
	List<Category> findBycategoryname(String categoryname);
}
