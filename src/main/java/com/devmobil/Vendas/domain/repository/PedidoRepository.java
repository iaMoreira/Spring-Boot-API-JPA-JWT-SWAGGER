package com.devmobil.Vendas.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devmobil.Vendas.domain.entity.Cliente;
import com.devmobil.Vendas.domain.entity.Pedido;

public interface PedidoRepository extends BaseRepository<Pedido>{
	List<Pedido> findByCliente(Cliente cliente);
	
	@Query(" select p from Pedido p left join fetch p.itens where p.id = :id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") long id);
}
