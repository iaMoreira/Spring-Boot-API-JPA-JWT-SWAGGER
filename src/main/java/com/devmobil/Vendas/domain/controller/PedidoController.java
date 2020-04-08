package com.devmobil.Vendas.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmobil.Vendas.domain.dto.PedidoDto;
import com.devmobil.Vendas.domain.entity.Pedido;
import com.devmobil.Vendas.domain.service.IPedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	@Autowired
	private IPedidoService service;
	
	@PostMapping
	public long save(@RequestBody PedidoDto dto) {
		Pedido pedido = service.salvar(dto);
		return pedido.getId();
	}


}
