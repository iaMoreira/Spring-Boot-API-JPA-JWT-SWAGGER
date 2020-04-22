package com.devmobil.Vendas.domain.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDTO {
	
    @NotNull(message = "O campo senha atual é obrigátorio.")
	private String currentPassword;
    
    @NotNull(message = "O campo nova senha é obrigátorio.")
	private String newPassword;
}
