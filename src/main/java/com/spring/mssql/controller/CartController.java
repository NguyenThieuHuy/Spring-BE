package com.spring.mssql.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mssql.model.Cart;
import com.spring.mssql.repository.CartRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CartController {

	@Autowired
	CartRepository Repository;

	@GetMapping("/Cart")
	public ResponseEntity<List<Cart>> getAllCart() {
		try {
			List<Cart> Cart = new ArrayList<Cart>();
			Repository.findAll().forEach(Cart::add);

			if (Cart.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Cart, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/Cart/{cartid}")
	public ResponseEntity<Cart> getCartById(@PathVariable("cartid") Integer cartid) {

		Cart CartData = Repository.findBycartid(cartid);

		if (!CartData.toString().isEmpty()) {
			return new ResponseEntity<>(CartData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Cart")
	public ResponseEntity<Cart> createCart(@RequestBody Cart data) {
		try {
			Cart _Cart = Repository
					.save(new Cart(data.getproductid(), data.getmemberid(), data.getcartstatusid()));
			return new ResponseEntity<>(_Cart, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Cart/{cartid}")
	public ResponseEntity<Cart> updateCart(@PathVariable("cartid") Integer cartid, @RequestBody Cart data) {
		Cart CartData = Repository.findBycartid(cartid);

		if (!CartData.toString().isEmpty()) {
			CartData.setproductid(data.getproductid());
			CartData.setmemberid(data.getmemberid());
			CartData.setcartstatusid(data.getcartstatusid());
			return new ResponseEntity<>(Repository.save(CartData), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Cart/{id}")
	public ResponseEntity<HttpStatus> deleteCart(@PathVariable("id") Integer roleid) {
		try {
			Repository.deleteById(roleid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Cart")
	public ResponseEntity<HttpStatus> deleteAllCart() {
		try {
			Repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
