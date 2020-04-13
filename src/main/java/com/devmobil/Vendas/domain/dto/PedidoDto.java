package com.devmobil.Vendas.domain.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.devmobil.Vendas.validation.NotEmptyList;

/*
 * {
	"cliente": 1,
	"total": 100,
	"items": [
		{
			"produto": 1,
			"quantidade": 10
		}
	]
}
 * */
public class PedidoDto {

	@NotNull(message = "Informe  o código do cliente.")
	private Long cliente;
	@NotNull(message = "Campo total do pedido é obrigatório.")
	private BigDecimal total;
	@NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
	private List<ItemPedidoDto> items;
	
	public long getCliente() {
		return cliente;
	}
	public void setCliente(long cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<ItemPedidoDto> getItems() {
		return items;
	}
	public void setItems(List<ItemPedidoDto> items) {
		this.items = items;
	}
	
	
}
