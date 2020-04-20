package com.devmobil.Vendas.domain.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.devmobil.Vendas.domain.entity.Order;
import com.devmobil.Vendas.resource.BaseDTO;
import com.devmobil.Vendas.validation.NotEmptyList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO implements BaseDTO<Order> {

	@NotNull(message = "Informe  o código do cliente.")
	private Long user;
	
	@NotNull(message = "Campo total do pedido é obrigatório.")
	private BigDecimal total;
	
	@NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
	private List<ItemPedidoDto> items;

	@Override
	public Order getEntity() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
