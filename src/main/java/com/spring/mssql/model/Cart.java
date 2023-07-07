package com.spring.mssql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
  @Id
  @Column(name = "cartid")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int cartid;

  @Column(name = "productid")
  private int productid;
  
  @Column(name = "memberid")
  private int memberid;
  
  @Column(name = "cartstatusid")
  private int cartstatusid;
  

  public Cart() {

  }

  public Cart(int productid, int memberid, int cartstatusid) {
    this.productid = productid;
    this.memberid  = memberid;
    this.cartstatusid = cartstatusid;
  }

  public int getcartid() {
    return cartid;
  }
  
  public int getproductid() {
	    return productid;
  }
  
  public int getmemberid() {
	    return memberid;
  }
  
  public int getcartstatusid() {
	    return cartstatusid;
  }

  public void setproductid(int productid) {
    this.productid = productid;
  }

  public void setmemberid(int memberid) {
	    this.memberid = memberid;
  }
  
  public void setcartstatusid(int cartstatusid) {
	    this.cartstatusid = cartstatusid;
  }
  
  @Override
  public String toString() {
    return "Cart [id=" + cartid + ", product=" + productid + 
    		", member=" + memberid + ", cartstatus=" + cartstatusid + "]";
  }

}
