package com.devmobil.Vendas.domain.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devmobil.Vendas.domain.dto.TokenDTO;
import com.devmobil.Vendas.domain.dto.UserDTO;
import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.exception.PasswordInvalidException;
import com.devmobil.Vendas.security.JwtService;
import com.devmobil.Vendas.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private JwtService jwtService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User store(@Valid @RequestBody User user) {
		return service.save(user);
	}
	
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
