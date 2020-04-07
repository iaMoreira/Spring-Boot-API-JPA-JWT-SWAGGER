package com.devmobil.Vendas.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings("serial")
public class BaseEntity implements Serializable {

	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO)  
	@Column(name = "id")
	protected long id;

	public long getId() { return id; }
	public void setId(long id) { this.id = id; }  
}