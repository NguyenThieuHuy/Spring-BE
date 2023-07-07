package com.spring.mssql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mssql.model.Members;

public interface MemberRepository extends JpaRepository<Members, Integer> {
  Members findBymemberid (int memberid);
  List<Members> findByemailid (String emailid);
}