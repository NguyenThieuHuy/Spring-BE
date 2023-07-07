package com.spring.mssql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "memberrole")
public class MemberRole {

  @Id
  @Column(name = "memberroleid")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int memberroleid;

  @Column(name = "memberid")
  private int memberid;

  @Column(name = "roleid")
  private int roleid;

  public MemberRole() {

  }

  public MemberRole(int memberid, int roleid) {
    this.memberid = memberid;
    this.roleid = roleid;
  }

  public int getmemberroleid() {
    return memberroleid;
  }

  public int getmemberid() {
    return memberid;
  }

  public void setmemberid(int memberid) {
    this.memberid = memberid;
  }

  public int getroleid() {
    return roleid;
  }

  public void setroleid(int roleid) {
    this.roleid = roleid;
  }


  @Override
  public String toString() {
    return "MemberRole [id=" + memberroleid + ", memberid=" + memberid + ", roleid=" + roleid + "]";
  }

}
