package com.devmobil.Vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devmobil.Vendas.domain.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	List<Cliente> findByNomeLike(String nome);
	
	@Query(value = " select c from Cliente c where c.nome like :nome ")
	List<Cliente> encontratPorNome(@Param("nome") String nome);
	
	@Query(" select c from Cliente c left join fetch c.pedido where c.id = :id")
	Cliente findClienteFetchPedidos(@Param("id") Integer id);
}
