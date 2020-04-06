package com.devmobil.Vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmobil.Vendas.domain.entity.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
