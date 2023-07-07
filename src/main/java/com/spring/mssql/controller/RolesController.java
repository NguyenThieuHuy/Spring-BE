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

import com.spring.mssql.model.Roles;
import com.spring.mssql.repository.RolesRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RolesController {

	@Autowired
	RolesRepository Repository;

	@GetMapping("/Roles")
	public ResponseEntity<List<Roles>> getAllRoles() {
		try {
			List<Roles> Roles = new ArrayList<Roles>();
			Repository.findAll().forEach(Roles::add);

			if (Roles.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Roles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/Role/{roleid}")
	public ResponseEntity<Roles> getRoleById(@PathVariable("roleid") Integer roleid) {

		Roles RolesData = Repository.findByroleid(roleid);

		if (!RolesData.toString().isEmpty()) {
			return new ResponseEntity<>(RolesData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Role")
	public ResponseEntity<Roles> createRoles(@RequestBody Roles data) {
		try {
			Roles _Role = Repository
					.save(new Roles(data.getroleid(), data.getrolename()));
			return new ResponseEntity<>(_Role, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Role/{roleid}")
	public ResponseEntity<Roles> updateRoles(@PathVariable("roleid") Integer roleid, @RequestBody Roles data) {
		Roles RolesData = Repository.findByroleid(roleid);

		if (!RolesData.toString().isEmpty()) {
			RolesData.setrolename(data.getrolename());
			return new ResponseEntity<>(Repository.save(RolesData), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Role/{id}")
	public ResponseEntity<HttpStatus> deleteRoles(@PathVariable("id") Integer roleid) {
		try {
			Repository.deleteById(roleid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Roles")
	public ResponseEntity<HttpStatus> deleteAllRoles() {
		try {
			Repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
