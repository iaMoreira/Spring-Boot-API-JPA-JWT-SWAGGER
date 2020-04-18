package com.devmobil.Vendas.domain.repository;

import java.util.Optional;

import com.devmobil.Vendas.domain.entity.User;

public interface UserRepository extends BaseRepository<User>{

	public Optional<User> findByEmail(String email);
}
