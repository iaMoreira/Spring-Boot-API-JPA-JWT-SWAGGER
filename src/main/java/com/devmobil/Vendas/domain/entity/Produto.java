package com.devmobil.Vendas.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Produto extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(length = 100)
	private String nome;
	
	@Column(length = 250)
	private String descricao;
	
	@Column(name = "preco", precision = 20, scale= 2)
	private BigDecimal preco;
	

	
	
	
}
