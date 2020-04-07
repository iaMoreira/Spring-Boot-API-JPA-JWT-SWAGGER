package com.devmobil.Vendas.domain.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmobil.Vendas.domain.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Serializable>{

}