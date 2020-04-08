package com.devmobil.Vendas.domain.dto;

public class ItemPedidoDto {

	private long produto;
	private Integer quantidade;
	public long getProduto() {
		return produto;
	}
	public void setProduto(long produto) {
		this.produto = produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
