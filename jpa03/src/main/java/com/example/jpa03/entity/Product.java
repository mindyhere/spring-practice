package com.example.jpa03.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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
	public Product(long productCode) {
		this.productCode=productCode;
	}		
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productCode;

	private String productName;

	private int price;

	@Lob
	@Column(length = 8000)
	private String description;

	private String filename;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	List<Cart> cartList = new ArrayList<>();
	

}
