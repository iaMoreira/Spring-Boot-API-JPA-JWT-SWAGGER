package com.devmobil.Vendas.domain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmobil.Vendas.domain.dto.ProductDTO;
import com.devmobil.Vendas.domain.entity.Product;
import com.devmobil.Vendas.domain.service.ProductService;
import com.devmobil.Vendas.resource.BaseController;

@RestController
@RequestMapping("/api/products")
public class ProductController extends BaseController<Product, ProductDTO, ProductService> {
	

}
