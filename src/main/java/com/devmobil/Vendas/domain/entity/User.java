package com.devmobil.Vendas.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devmobil.Vendas.resource.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity{

	@Column(name = "name", length = 100)
	private String name;
	
	@Column(name = "email", length = 100)
	private String email;
		
	@JsonIgnore // funciona como um hidden e esconde o campo quando é rederizado em Json 
	@Column(name = "password", length = 100)
	private String password;
		
	@Column
	private Boolean admin;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
//	private Set<Order> orders;

	
}
