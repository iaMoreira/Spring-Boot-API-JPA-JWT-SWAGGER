package com.devmobil.Vendas.domain.dto;

import java.math.BigDecimal;
import java.util.List;

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

	private long cliente;
	private BigDecimal total;
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
