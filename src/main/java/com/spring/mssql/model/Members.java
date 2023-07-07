package com.spring.mssql.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "members")
public class Members {
  @Id
  @Column(name = "memberid")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int memberid;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;
  
  @Column(name = "emailid")
  private String emailid;
  
  @Column(name = "password")
  private String password;
  
  @Column(name = "createdon")
  private String createdon;
  
  @Column(name = "modifiedon")
  private String modifiedon;
  
  public Members() {

  }

  public Members(String firstname, String lastname, String emailid, String password) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.emailid = emailid;
    this.password = password;
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
    this.createdon = this.modifiedon = dateFormat.format(new Date());
  }
  
  public void setCreatedon() {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	this.modifiedon = dateFormat.format(new Date());
  }
  
  public long getMemberid() {
    return memberid;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String LastName) {
    this.lastname = LastName;
  }
  
  public String getEmailid() {
	  return emailid;
  }

  public void setEmailid(String EmailId) {
	  this.emailid = EmailId;
  }
  
  public String getPassword() {
	  return password;
  }

  public void setPassword(String Password) {
    this.password = Password;
  }

  @Override
  public String toString() {
    return "Member [firstname=" + firstname + ", LastName=" + lastname + ", EmailId=" + emailid + "]";
  }
}