package com.devmobil.Vendas.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.resource.BaseDTO;

@Data
@NoArgsConstructor
public class UserDTO implements BaseDTO<User>{
	
	@NotEmpty(message = "O campo nome é obrigátorio.")
	private String name;
	
	@NotNull(message = "Username é obrigátio.")
	private String username;
	
	
    @NotNull(message = "O campo senha é obrigátorio.")
	private String password; 
	
    private Boolean admin;
    
	@Override
	public User getEntity() {
		
		return null;
	}
	@Override
	public User getEntity(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
