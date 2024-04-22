package com.example.myshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int productCode;
	
	private String productName;
	
	private int price;
	
	@Lob
	private String description;
	
	private String fileName;

	public Product(int productCode) {
		super();
		this.productCode = productCode;
	}
	
//	@ToString.Exclude
//	@OneToMany(mappedBy = "product", cascade=CascadeType.ALL)
//	List<OrderDetail> orderDetailList=new ArrayList<>();
	
}
