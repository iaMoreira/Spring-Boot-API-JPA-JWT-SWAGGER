package com.devmobil.Vendas.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devmobil.Vendas.domain.dto.AtualizaStatusPedidoDTO;
import com.devmobil.Vendas.domain.dto.OrderDTO;
import com.devmobil.Vendas.domain.entity.Order;
import com.devmobil.Vendas.domain.enums.StatusOrder;
import com.devmobil.Vendas.domain.service.OrderService;
import com.devmobil.Vendas.resource.BaseController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/orders")
@Api("Pedidos")
public class OrderController extends BaseController<Order, OrderDTO, OrderService>{
	
	@Autowired
	private OrderService service;
	
	
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Long id, @RequestBody AtualizaStatusPedidoDTO dto) {
		String novoStatus = dto.getStatus();
		service.atualizaStatus(id, StatusOrder.valueOf(novoStatus));
	}
}
