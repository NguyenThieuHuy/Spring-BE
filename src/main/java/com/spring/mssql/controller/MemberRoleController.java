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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mssql.model.MemberRole;
import com.spring.mssql.repository.MemberRoleRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class MemberRoleController {

	@Autowired
	MemberRoleRepository Repository;

	@GetMapping("/MemberRoles")
	public ResponseEntity<List<MemberRole>> getAllMemberRoles(@RequestParam(required = false) Integer memberid) {
		try {
			List<MemberRole> MemberRoles = new ArrayList<MemberRole>();

			if (memberid == null)
				Repository.findAll().forEach(MemberRoles::add);
			else
				Repository.findBymemberid(memberid).forEach(MemberRoles::add);

			if (MemberRoles.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(MemberRoles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/RoleMembers")
	public ResponseEntity<List<MemberRole>> getAllRoleMembers(@RequestParam(required = false) Integer roleid) {
		try {
			List<MemberRole> MemberRoles = new ArrayList<MemberRole>();

			if (roleid == null)
				Repository.findAll().forEach(MemberRoles::add);
			else
				Repository.findByroleid(roleid).forEach(MemberRoles::add);

			if (MemberRoles.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(MemberRoles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/MemberRole/{memberroleid}")
	public ResponseEntity<MemberRole> getMemberRoleById(@PathVariable("memberroleid") Integer memberroleid) {

		MemberRole MemberRoleData = Repository.findBymemberroleid(memberroleid);

		if (!MemberRoleData.toString().isEmpty()) {
			return new ResponseEntity<>(MemberRoleData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/MemberRole")
	public ResponseEntity<MemberRole> createMemberRole(@RequestBody MemberRole data) {
		try {
			MemberRole _MemberRole = Repository
					.save(new MemberRole(data.getmemberid(), data.getroleid()));
			return new ResponseEntity<>(_MemberRole, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/MemberRole/{memberroleid}")
	public ResponseEntity<MemberRole> updateMemberRole(@PathVariable("memberroleid") Integer memberroleid, @RequestBody MemberRole data) {
		MemberRole MemberRoleData = Repository.findBymemberroleid(memberroleid);

		if (!MemberRoleData.toString().isEmpty()) {
			MemberRoleData.setmemberid(data.getmemberid());
			MemberRoleData.setroleid(data.getroleid());
			return new ResponseEntity<>(Repository.save(MemberRoleData), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/MemberRole/{id}")
	public ResponseEntity<HttpStatus> deleteMemberRole(@PathVariable("id") Integer memberroleid) {
		try {
			Repository.deleteById(memberroleid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/MemberRoles")
	public ResponseEntity<HttpStatus> deleteAllMemberRoles() {
		try {
			Repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
