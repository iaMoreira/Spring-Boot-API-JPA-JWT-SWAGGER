package com.devmobil.Vendas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

	private Long id;
	private String email;
	private String name;
	private String access_token;
}
