package com.devmobil.Vendas.domain.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.service.MyUserDetailsService;


@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private MyUserDetailsService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User store(@Valid @RequestBody User user) {
		return service.save(user);
	}
}
