package com.devmobil.Vendas.domain.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devmobil.Vendas.domain.dto.TokenDTO;
import com.devmobil.Vendas.domain.dto.UserDTO;
import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.domain.repository.UserRepository;
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
	
	@Autowired
	private UserRepository repository;
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public User store(@Valid @RequestBody User user) {
//		return service.save(user);
//	}
	
	@PostMapping("/login")
	public TokenDTO login(@RequestBody UserDTO credentials) {
		try {
			User user = User.builder()
					.email(credentials.getEmail())
					.password(credentials.getPassword()).build();
			service.auth(user);
			String token = jwtService.createToken(user);
			
			Optional<User> optional = repository.findByEmail(credentials.getEmail());

			return new TokenDTO(optional.get().getId(), credentials.getEmail(), optional.get().getName(), token);
			
		} catch (UsernameNotFoundException | PasswordInvalidException e) {			
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());

		}
	}
	
	@GetMapping(value = "/auth")
    public TokenDTO auth() {
    	org.springframework.security.core.userdetails.User username =  (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                .getAuthentication()	
                .getPrincipal();
    	Optional<User> optional = repository.findByEmail(username.getUsername());
    	User user = optional.get();
    	
    	return new TokenDTO(user.getId(), user.getEmail(), user.getName(), jwtService.createToken(user));
    }
}
