package com.devmobil.Vendas.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.resource.BaseDTO;

@Data
@NoArgsConstructor
public class UserDTO implements BaseDTO<User>{
	
	@NotEmpty(message = "O campo nome é obrigátorio.")
	private String name;
	
	@NotNull(message = "Email é obrigátio.")
	private String email;
	
	
    @NotNull(message = "O campo senha é obrigátorio.")
	private String password; 
	
    private Boolean admin;
    
	@Override
	public User getEntity() {
		User user = new User();
		user.setAdmin(admin);
		user.setCreatedAt(LocalDateTime.now());
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		return user;
	}
}
