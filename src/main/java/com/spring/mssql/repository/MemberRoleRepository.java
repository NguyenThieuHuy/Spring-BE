package com.spring.mssql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mssql.model.MemberRole;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Integer> {
  List<MemberRole> findBymemberid(int memberid);
  List<MemberRole> findByroleid(int roleid);
  MemberRole findBymemberroleid(int memberroleid);
}
