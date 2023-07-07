package com.spring.mssql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mssql.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	Cart findBycartid(int cartid);
	List<Cart> findBymemberid(int memberid);
}
