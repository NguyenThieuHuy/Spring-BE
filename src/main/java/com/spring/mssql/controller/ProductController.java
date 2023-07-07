package com.spring.mssql.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mssql.model.Product;
import com.spring.mssql.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductRepository Repository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping("/Products")
	public List<Product> getAllProduct() {
		List<Product> Product = new ArrayList<Product>();
		Repository.findAll().forEach(Product::add);
		if (Product.isEmpty()) {
			return null;
		}
		return Product;
	}
	
	@GetMapping("/Product/{productid}")
	public Product getProductById(@PathVariable("productid") Integer productid) {
		Product ProductData = Repository.findByproductid(productid);
		if (!ProductData.toString().isEmpty()) {
			return ProductData;
		} else {
			return null;
		}
	}
	
	@GetMapping("/CateProducts/{categoryid}")	
	public List<Product> getProductByCategoryID(@PathVariable("categoryid") Integer categoryid) {
		List<Product> Product = new ArrayList<Product>();
		Repository.findBycategoryid(categoryid).forEach(Product::add);
		return Product;
	}
	
	@PostMapping("/Product")
	public ResponseEntity<Product> createProduct(@RequestBody Product data) {
		
		try {
			Product _Product = Repository.
					save(new Product(data.getProductname(),data.getCategoryID(), data.getDescription(),data.getProductimage(),data.getQuantity()));
			return new ResponseEntity<>(_Product, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/Product/{productid}")
	public ResponseEntity<Product> updateProduct(@PathVariable("productid") Integer productid, @RequestBody Product data) {
		Product ProductData = Repository.findByproductid(productid);

		if (!ProductData.toString().isEmpty()) {
			ProductData.setCreatedon();
			if(data.getProductname()  != null) ProductData.setProductname(data.getProductname());
			if(data.getCategoryID() != 0) ProductData.setCategoryID(data.getCategoryID());
			if(data.getDescription() != null) ProductData.setDescription(data.getDescription());
			if(data.getProductimage() != null) ProductData.setProductimage(data.getProductimage());
			if(data.getQuantity() != 0) ProductData.setQuantity(data.getQuantity());
			
			return new ResponseEntity<>(Repository.save(ProductData), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Transactional
	@DeleteMapping("/Product/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Integer productid) {
		try {
//			Repository.deleteById(productid);
			entityManager.joinTransaction();
			entityManager.createQuery("DELETE FROM Product WHERE productid = " + productid).executeUpdate();
			System.out.println("Success: deleteProduct");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println("Error: deleteProduct|" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	@DeleteMapping("/Product")
	public ResponseEntity<HttpStatus> deleteAllProduct() {
		try {
//			Repository.deleteAll();
			entityManager.joinTransaction();
			entityManager.createQuery("DELETE FROM Product").executeUpdate();
			System.out.println("Success: deleteAllProduct");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println("Error: deleteAllProduct|" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
