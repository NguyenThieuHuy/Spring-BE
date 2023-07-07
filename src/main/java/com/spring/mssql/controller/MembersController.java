package com.spring.mssql.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.spring.mssql.model.Members;
import com.spring.mssql.repository.MemberRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class MembersController {

	@Autowired
	MemberRepository Repository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping("/Members")
	public List<Members> getAllMembers() {
		List<Members> Members = new ArrayList<Members>();
		Repository.findAll().forEach(Members::add);
		if (Members.isEmpty()) {
			return null;
		}
		return Members;
	}
	
	public Members getMembersById(Integer memberid) {
		Members MembersData = Repository.findBymemberid(memberid);
		if (!MembersData.toString().isEmpty()) {
			return MembersData;
		} else {
			return null;
		}
	}
	
	public List<Members> getMembersByEmailId(String emailid) {
		List<Members> Members = new ArrayList<Members>();
		Repository.findByemailid(emailid).forEach(Members::add);
		return Members;
	}
	
	@PostMapping("/Login")
    public boolean authenticate(@RequestBody Members data) {
		
		List<Members> MembersData = new ArrayList<Members>();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
		
		if(data.getEmailid()  != null) {
			MembersData = Repository.findByemailid(data.getEmailid());
		}
		
		if (!MembersData.isEmpty()) {
			if(data.getPassword() != null) {
				for(Members m : MembersData) {
					return bCryptPasswordEncoder.matches(data.getPassword(), m.getPassword());
				}
			}
		}		
		return false;
    }

	
	@PostMapping("/Member")
	public ResponseEntity<Members> login(@RequestBody Members data) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
		List<Members> Members = getMembersByEmailId(data.getEmailid());
		if(Members.isEmpty()) {
			System.out.println("Error: login| Email not available");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		for (Members m: Members) {
			System.out.println("Check 1: " + !m.toString().isEmpty() + "\nCheck 2: " + bCryptPasswordEncoder.matches(data.getPassword(), m.getPassword()));	
			if (bCryptPasswordEncoder.matches(data.getPassword(), m.getPassword())) {
				return new ResponseEntity<>(m, HttpStatus.ACCEPTED);
			}
		}
		System.out.println("Error: login| Input Password: " + data.getPassword());
		return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping("/Members")
	public ResponseEntity<Members> createMember(@RequestBody Members data) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
		try {
			String enpa = bCryptPasswordEncoder.encode(data.getPassword());
			data.setPassword(enpa);
			Members _Members = Repository.save(new Members(data.getFirstname(),data.getLastname(),data.getEmailid(),data.getPassword()));
			return new ResponseEntity<>(_Members, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/Member/{memberid}")
	public ResponseEntity<Members> updateMember(@PathVariable("memberid") Integer memberid, @RequestBody Members data) {
		Members MembersData = Repository.findBymemberid(memberid);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
		if (!MembersData.toString().isEmpty()) {
			MembersData.setCreatedon();
			if(data.getEmailid()  != null) MembersData.setEmailid(data.getEmailid());
			if(data.getFirstname()!= null) MembersData.setFirstname(data.getFirstname());
			if(data.getLastname() != null) MembersData.setLastname(data.getLastname());
			if(data.getPassword() != null) MembersData.setPassword(bCryptPasswordEncoder.encode(data.getPassword()));
			return new ResponseEntity<>(Repository.save(MembersData), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Transactional
	@DeleteMapping("/Member/{id}")
	public ResponseEntity<HttpStatus> deleteMember(@PathVariable("id") Integer memberid) {
		try {
//			Repository.deleteById(memberid);
			entityManager.joinTransaction();
			entityManager.createQuery("DELETE FROM Members WHERE memberid = " + memberid).executeUpdate();
			System.out.println("Success: deleteMember");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println("Error: deleteMember|" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	@DeleteMapping("/Members")
	public ResponseEntity<HttpStatus> deleteAllMembers() {
		try {
//			Repository.deleteAll();
			entityManager.joinTransaction();
			entityManager.createQuery("DELETE FROM Members").executeUpdate();
			System.out.println("Success: deleteAllMembers");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println("Error: deleteAllMembers|" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
