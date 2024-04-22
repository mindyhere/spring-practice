package com.example.jpa03.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {
	private long productCode;
	private String productName;
	private int price;
	private String description;
	private String filename;
	private MultipartFile file1;
}