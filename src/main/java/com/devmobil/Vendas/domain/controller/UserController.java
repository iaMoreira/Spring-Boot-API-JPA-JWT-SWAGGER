package com.devmobil.Vendas.domain.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devmobil.Vendas.domain.dto.ChangePasswordDTO;
import com.devmobil.Vendas.domain.dto.TokenDTO;
import com.devmobil.Vendas.domain.dto.UserDTO;
import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.domain.service.UserService;
import com.devmobil.Vendas.exception.PasswordInvalidException;
import com.devmobil.Vendas.resource.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/users")
@Api("Api usuários")
public class UserController  extends BaseController<User, UserDTO, UserService>{

	@Autowired
	private UserService service;

	@PostMapping("/login")
	@ApiOperation("login do usuário")
	public ResponseEntity<TokenDTO> login( @RequestBody UserDTO credentials) {
		try {
			TokenDTO dto = service.login(credentials);		
			return ResponseEntity.ok(dto);
		} catch (UsernameNotFoundException | PasswordInvalidException e) {			
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());

		}
	}
	
	@GetMapping(value = "/user-auth")
	@ApiOperation("Dados do usuário corrente")
    public ResponseEntity<TokenDTO> auth() {
		TokenDTO dto = service.userAuth();
		return ResponseEntity.ok(dto);
    }
	
	
	@PatchMapping(value = "/change-password")
	@ApiOperation("Mudanção da senha")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO dto){
    	if(service.changePassword(dto)) {
    		return ResponseEntity.noContent().build();    		
    	}else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Senha atual não confere!");
    	}
	}

	
}
