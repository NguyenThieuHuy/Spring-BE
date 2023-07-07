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
@Table(name = "product")
public class Product {
	
  @Id
  @Column(name = "productid")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int productid;

  @Column(name = "productname")
  private String productname;

  @Column(name = "categoryid")
  private int categoryid;
  
  @Column(name = "isactive")
  private Boolean isactive;

  @Column(name = "isdelete")
  private Boolean isdelete;

  @Column(name = "createdon")
  private String createdon;
  
  @Column(name = "modifiedon")
  private String modifiedon;
  
  @Column(name = "description")
  private String description;
  
  @Column(name = "productimage")
  private String productimage;
  
  @Column(name = "quantity")
  private int quantity;
  
  public Product() {

  }

  public Product(String productname, int categoryid, String description, String productimage, int quantity) {
    this.productname = productname;
    this.categoryid = categoryid;
    this.description = description;
    this.productimage = productimage;
    this.quantity = quantity;
    
    this.isactive = true;
    this.isdelete = false;
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
    this.createdon = this.modifiedon = dateFormat.format(new Date());
  }
  
  public void setCreatedon() {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	this.modifiedon = dateFormat.format(new Date());
  }
  
  public long getProductID() {
    return productid;
  }

  public String getProductname() {
    return productname;
  }

  public void setProductname(String productname) {
    this.productname = productname;
  }
  
  public int getCategoryID() {
	    return categoryid;
  }
  
  public void setCategoryID(int categoryid) {
	    this.categoryid = categoryid;
  }
  
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description= description;
  }
  
  public String getProductimage() {
	  return productimage;
  }

  public void setProductimage(String productimage) {
	  this.productimage = productimage;
  }
  
  public int getQuantity() {
	  return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "Member [ProductName = " + productname + ", Description = " + description + ", Quantity=" + quantity + "]";
  }
}