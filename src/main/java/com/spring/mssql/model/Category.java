package com.spring.mssql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
  @Id
  @Column(name = "categoryid")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int categoryid;

  @Column(name = "categoryname")
  private String categoryname;
  
  @Column(name = "isactive")
  private Boolean isactive;

  @Column(name = "isdelete")
  private Boolean isdelete;

  public Category() {

  }

  public Category(String categoryname) {
    this.categoryname = categoryname;
    this.isactive = true;
    this.isdelete = false;
  }

  public int getcategoryid() {
    return categoryid;
  }
  
  public String getcategoryname() {
	  return categoryname;
  }

  public void setcategoryname(String categoryname) {
    this.categoryname = categoryname;
  }


  @Override
  public String toString() {
    return "Category [id=" + categoryid + ", name=" + categoryname + "]";
  }

}
