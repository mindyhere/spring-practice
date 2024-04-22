package com.example.jpa03.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cartId;

//	@ToString.Exclude
	//@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private Member member;

	@ToString.Exclude
	//@ManyToOne(fetch = FetchType.LAZY) // 지연로딩(필요할 때 조인)
	@ManyToOne(fetch = FetchType.EAGER) //즉시로딩(즉시 조인)
	@JoinColumn(name = "productCode")
	private Product product;

	private int amount;
}