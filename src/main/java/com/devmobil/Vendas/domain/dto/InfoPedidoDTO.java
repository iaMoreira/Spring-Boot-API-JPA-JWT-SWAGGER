package com.devmobil.Vendas.domain.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.devmobil.Vendas.domain.entity.Cliente;
import com.devmobil.Vendas.domain.entity.Pedido;

public class InfoPedidoDTO {
	private long id;
	private Cliente cliente;
	private BigDecimal total;
	private String dataPedido;
	private String status;
	private List<InfoItemPedidoDTO> itens;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<InfoItemPedidoDTO> getItens() {
		return itens;
	}
	public void setItens(List<InfoItemPedidoDTO> itens) {
		this.itens = itens;
	}
	public String getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static InfoPedidoDTO converter (Pedido pedido) {
		InfoPedidoDTO infoPedidoDTO = new InfoPedidoDTO();
		infoPedidoDTO.setId(pedido.getId());
		infoPedidoDTO.setCliente(pedido.getCliente());
		infoPedidoDTO.setTotal(pedido.getTotal());
		infoPedidoDTO.setStatus(pedido.getStatus().name());
		infoPedidoDTO.setDataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
		infoPedidoDTO.setItens(InfoItemPedidoDTO.collection(pedido.getItens()));
		return infoPedidoDTO;
	}
	
	
		

}
