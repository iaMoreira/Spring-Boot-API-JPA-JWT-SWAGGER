package com.devmobil.Vendas.domain.service;


import com.devmobil.Vendas.domain.dto.PedidoDto;
import com.devmobil.Vendas.domain.entity.Pedido;

public interface IPedidoService {
	
	Pedido salvar(PedidoDto dto);
}
