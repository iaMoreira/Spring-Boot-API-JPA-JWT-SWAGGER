package com.devmobil.Vendas.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@NotEmpty(message = "O campo nome é obrigátorio.")
	private String nome;
	
	@Column(length = 250)
	@NotEmpty(message = "O campo descrição é obrigátorio.")
	private String descricao;
	
	@Column(name = "preco", precision = 20, scale= 2)
	@NotNull(message = "O campo preço é obrigatório.")
	private BigDecimal preco;
	

	
	
	
}
