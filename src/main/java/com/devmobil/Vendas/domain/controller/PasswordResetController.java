package com.devmobil.Vendas.domain.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devmobil.Vendas.domain.dto.PasswordResetDTO;
import com.devmobil.Vendas.domain.entity.PasswordResetToken;
import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.domain.repository.PasswordResetTokenRepository;
import com.devmobil.Vendas.domain.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/reset-password")
@Api("Resetar senha")
public class PasswordResetController {
	
	
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private PasswordResetTokenRepository tokenRepository;
   @Autowired
   private PasswordEncoder passwordEncoder;

	@PostMapping
    @Transactional
    @ApiOperation("Alterar Senha")
    public ResponseEntity<?> handlePasswordReset(@RequestBody @Valid PasswordResetDTO dto) {

		PasswordResetToken token = tokenRepository.findByToken(dto.getToken());
		if (token == null) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token não encontrado!");
	    }
		
		if (token.isExpired()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O token expirou, faça um novo pedido!");
	    }
		
        User user = token.getUser();
        String updatedPassword = passwordEncoder.encode(dto.getPassword());
        userRepository.updatePassword(updatedPassword, user.getId());
        tokenRepository.delete(token);

        return ResponseEntity.ok("");
    }

}
