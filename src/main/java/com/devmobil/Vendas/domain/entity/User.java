package com.devmobil.Vendas.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity{

	@Column
	@NotNull(message = "Email é obrigátio.")
	private String email;
		
	@Column 
	@NotNull(message = "Senha é obrigátio.")
	private String password;
	
	@Column
	private Boolean admin;
	
}
