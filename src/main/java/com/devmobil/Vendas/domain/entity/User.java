package com.devmobil.Vendas.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

	@Column
	@NotNull(message = "Username é obrigátio.")
	private String username;
		
	@Column 
	@NotNull(message = "Senha é obrigátio.")
	private String password;
	
	@Column
	private Boolean admin;
	
}
