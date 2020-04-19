package com.devmobil.Vendas.resource;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BaseRepository <T extends BaseEntity> extends JpaRepository <T, Serializable> {

}
