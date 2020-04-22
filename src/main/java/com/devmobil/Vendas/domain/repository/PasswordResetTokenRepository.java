package com.devmobil.Vendas.domain.repository;

import org.springframework.stereotype.Repository;

import com.devmobil.Vendas.domain.entity.PasswordResetToken;
import com.devmobil.Vendas.resource.BaseRepository;

@Repository
public interface PasswordResetTokenRepository extends  BaseRepository<PasswordResetToken>{
	
    PasswordResetToken findByToken(String token);
}
