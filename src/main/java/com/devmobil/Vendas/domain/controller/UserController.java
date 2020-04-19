package com.devmobil.Vendas.domain.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devmobil.Vendas.domain.dto.TokenDTO;
import com.devmobil.Vendas.domain.dto.UserDTO;
import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.domain.service.UserService;
import com.devmobil.Vendas.exception.PasswordInvalidException;
import com.devmobil.Vendas.resource.BaseController;
import com.devmobil.Vendas.security.JwtService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/api/users")
@Api("Api usuários")
public class UserController  extends BaseController<User, UserDTO, UserService>{

	@Autowired
	private UserService service;

	@Autowired
	private JwtService jwtService;
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public User store(@Valid @RequestBody User user) {
//		return service.save(user);
//	}
	
	@PostMapping("/login")
	public TokenDTO login(@RequestBody UserDTO credentials) {
		try {
			User user = User.builder()
					.username(credentials.getUsername())
					.password(credentials.getPassword()).build();
			service.auth(user);
			String token = jwtService.createToken(user);
			
			return new TokenDTO(user.getUsername(), token);
			
		} catch (UsernameNotFoundException | PasswordInvalidException e) {			
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());

		}
	}
}
