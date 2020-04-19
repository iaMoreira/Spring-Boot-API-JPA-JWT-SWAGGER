package com.devmobil.Vendas.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devmobil.Vendas.resource.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

	@Column(name = "name", length = 100)
	private String name;
	
	@Column(name = "description", length = 250)
	private String description;
	
	@Column(name = "price", precision = 20, scale= 2)
	private BigDecimal price;
	
}
