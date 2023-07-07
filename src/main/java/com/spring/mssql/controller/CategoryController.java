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

import com.spring.mssql.model.Category;
import com.spring.mssql.repository.CategoryRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	CategoryRepository Repository;

	@GetMapping("/Category")
	public ResponseEntity<List<Category>> getAllCategory() {
		try {
			List<Category> Category = new ArrayList<Category>();
			Repository.findAll().forEach(Category::add);

			if (Category.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Category, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/Category/{categoryid}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("categoryid") Integer categoryid) {

		Category CategoryData = Repository.findBycategoryid(categoryid);

		if (!CategoryData.toString().isEmpty()) {
			return new ResponseEntity<>(CategoryData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Category")
	public ResponseEntity<Category> createCategory(@RequestBody Category data) {
		try {
			Category _Category = Repository
					.save(new Category(data.getcategoryname()));
			return new ResponseEntity<>(_Category, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Category/{categoryid}")
	public ResponseEntity<Category> updateCategory(@PathVariable("categoryid") Integer categoryid, @RequestBody Category data) {
		Category CategoryData = Repository.findBycategoryid(categoryid);

		if (!CategoryData.toString().isEmpty()) {
			CategoryData.setcategoryname(data.getcategoryname());
			return new ResponseEntity<>(Repository.save(CategoryData), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Category/{id}")
	public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") Integer roleid) {
		try {
			Repository.deleteById(roleid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Category")
	public ResponseEntity<HttpStatus> deleteAllCategory() {
		try {
			Repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
