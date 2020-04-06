package com.devmobil.Vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmobil.Vendas.domain.entity.Cliente;
import com.devmobil.Vendas.domain.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	List<Pedido> findByCliente(Cliente cliente);
}
