package com.devmobil.Vendas.domain.service;


import java.util.Optional;

import com.devmobil.Vendas.domain.dto.PedidoDto;
import com.devmobil.Vendas.domain.entity.Pedido;

public interface IPedidoService {
	
	Pedido salvar(PedidoDto dto);
	Optional<Pedido> getPedido(long id);
}
