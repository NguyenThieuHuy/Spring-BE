package com.spring.mssql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mssql.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Product findByproductid (int productid);
	List<Product> findBycategoryid (int categoryid);
}