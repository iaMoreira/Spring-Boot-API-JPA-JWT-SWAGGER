package com.devmobil.Vendas.domain.service;


import java.util.Optional;

import com.devmobil.Vendas.domain.dto.PedidoDto;
import com.devmobil.Vendas.domain.entity.Pedido;
import com.devmobil.Vendas.domain.enums.StatusPedido;

public interface IPedidoService {
	
	Pedido salvar(PedidoDto dto);
	Optional<Pedido> getPedido(long id);
	void atualizaStatus(Long id, StatusPedido statusPedido);
}
