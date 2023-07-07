package com.spring.mssql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mssql.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
  Roles findByroleid(int roleid);
  List<Roles> findByrolename(String rolename);
}
