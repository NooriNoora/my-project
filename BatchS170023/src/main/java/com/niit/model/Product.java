package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="Product")
public class Product {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int productId;
	@NotEmpty(message="ProductName shouldnot be empty")
	private String productname;
	@NotNull
	@Length(message="Category shouldnot be empty")
	private String category;
	@NotNull
	@Length(message="Description shouldnot be empty")
	private String description;
	@NotNull
	@Length(message="Cost shouldnot be empty")
	private String cost;
	@NotNull
	@Length(message="Discount shouldnot be empty")
	private String discount;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
				}
