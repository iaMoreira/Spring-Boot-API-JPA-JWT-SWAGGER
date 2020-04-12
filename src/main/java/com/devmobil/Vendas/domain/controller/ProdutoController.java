package com.devmobil.Vendas.domain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmobil.Vendas.domain.entity.Produto;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController extends BaseController<Produto> {
	

}
