package com.devmobil.Vendas.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordForgotDTO {
	
	@Email
    @NotEmpty
    private String email;
}
