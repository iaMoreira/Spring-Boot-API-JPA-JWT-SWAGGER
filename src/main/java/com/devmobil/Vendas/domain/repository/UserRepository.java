package com.devmobil.Vendas.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.resource.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User>{

	public Optional<User> findByEmail(String email);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") Long id);

}
