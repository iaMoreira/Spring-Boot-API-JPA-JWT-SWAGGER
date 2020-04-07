package com.devmobil.Vendas.domain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devmobil.Vendas.domain.entity.Cliente;
import com.devmobil.Vendas.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController extends BaseController<Cliente, ClienteRepository> {
	
	@GetMapping("/hello/{nome}")
	@RequestMapping(value ="/hello/{nome}", method = RequestMethod.GET)
	@ResponseBody
	public String hello(@PathVariable("nome") String nome) {
		return nome;
	}
	
}
