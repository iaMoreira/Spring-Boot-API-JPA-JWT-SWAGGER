package com.devmobil.Vendas.domain.dto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.devmobil.Vendas.domain.entity.ItemPedido;

public class InfoItemPedidoDTO {
	private String nome;
	private BigDecimal preco;
	private Integer quantidade;

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
	public static List<InfoItemPedidoDTO> collection(List<ItemPedido> itens){
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map( 
			item -> { 
				InfoItemPedidoDTO info = new InfoItemPedidoDTO();
				info.setNome(item.getProduto().getNome());
				info.setPreco(item.getProduto().getPreco());
				info.setQuantidade(item.getQuantidade());
				return info;
			}).collect(Collectors.toList());
	}
	
	

}
