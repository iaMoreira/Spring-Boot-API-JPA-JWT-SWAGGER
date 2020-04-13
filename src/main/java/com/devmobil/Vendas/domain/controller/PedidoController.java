package com.devmobil.Vendas.domain.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devmobil.Vendas.domain.dto.AtualizaStatusPedidoDTO;
import com.devmobil.Vendas.domain.dto.InfoPedidoDTO;
import com.devmobil.Vendas.domain.dto.PedidoDto;
import com.devmobil.Vendas.domain.entity.Pedido;
import com.devmobil.Vendas.domain.enums.StatusPedido;
import com.devmobil.Vendas.domain.service.IPedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	@Autowired
	private IPedidoService service;
	
	@PostMapping
	public long save(@RequestBody @Valid   PedidoDto dto) {
		Pedido pedido = service.salvar(dto);
		return pedido.getId();
	}

	@GetMapping("/{id}")
	public InfoPedidoDTO show(@PathVariable Long id) {
		return service.getPedido(id)
				.map(pedido -> InfoPedidoDTO.converter(pedido))
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido "+ id+" não encontrado."));
				 
	}
	
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Long id, @RequestBody AtualizaStatusPedidoDTO dto) {
		String novoStatus = dto.getStatus();
		service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
	}
}
