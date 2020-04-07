package com.devmobil.Vendas.domain.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmobil.Vendas.domain.entity.BaseEntity;

public interface BaseRepository <T extends BaseEntity> extends JpaRepository<T, Serializable> {

}
