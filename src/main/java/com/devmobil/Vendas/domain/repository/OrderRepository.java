package com.devmobil.Vendas.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devmobil.Vendas.domain.entity.Order;
import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.resource.BaseRepository;

public interface OrderRepository extends BaseRepository<Order>{
	List<Order> findByUser(User user);
	
	@Query(" select o from Order o left join fetch o.itens where o.id = :id ")
    Optional<Order> findByIdFetchItens(@Param("id") Long id);
}
