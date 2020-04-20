package com.devmobil.Vendas.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.devmobil.Vendas.domain.entity.Product;
import com.devmobil.Vendas.resource.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO implements BaseDTO<Product> {

	@NotEmpty(message = "O campo nome é obrigátorio.")
	private String name;
	
	@NotEmpty(message = "O campo descrição é obrigátorio.")
	private String description;
	
	@NotNull(message = "O campo preço é obrigatório.")
	private BigDecimal price;
	
	@Override
	public Product getEntity() {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		product.setCreatedAt(LocalDateTime.now());
		return product;
	}

}
