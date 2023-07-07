package com.spring.mssql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles {
  @Id
  @Column(name = "roleid")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int roleid;

  @Column(name = "rolename")
  private String rolename;

  public Roles() {

  }

  public Roles(int roleid, String rolename) {
    this.roleid = roleid;
    this.rolename = rolename;
  }

  public int getroleid() {
    return roleid;
  }
  
  public String getrolename() {
	  return rolename;
  }

  public void setrolename(String rolename) {
    this.rolename = rolename;
  }


  @Override
  public String toString() {
    return "Role [id=" + roleid + ", name=" + rolename + "]";
  }

}
