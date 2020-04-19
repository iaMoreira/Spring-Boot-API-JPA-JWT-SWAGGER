package com.devmobil.Vendas.domain.repository;

import java.util.Optional;

import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.resource.BaseRepository;

public interface UserRepository extends BaseRepository<User>{

	public Optional<User> findByUsername(String username);
}
