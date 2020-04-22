package com.devmobil.Vendas.domain.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devmobil.Vendas.domain.dto.PasswordForgotDTO;
import com.devmobil.Vendas.domain.entity.PasswordResetToken;
import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.domain.repository.PasswordResetTokenRepository;
import com.devmobil.Vendas.domain.repository.UserRepository;
import com.devmobil.Vendas.security.model.Mail;
import com.devmobil.Vendas.security.service.EmailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/forgot-password")
@Api("Recuperar senha")
public class PasswordForgotController {
	
	@Autowired
	private UserRepository userRepository;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private EmailService emailService;

    @PostMapping
    @ApiOperation("Envia email")
    @Transactional
    public ResponseEntity<?> processForgotPasswordForm(@Valid @RequestBody PasswordForgotDTO dto) {

        Optional<User> optional = userRepository.findByEmail(dto.getEmail());
        if (!optional.isPresent()){
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço de email não encontrado!");
        }
        User user = optional.get();

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://memorynotfound.com");
        String url =  "http://localhost:4200";
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);

		return ResponseEntity.ok("");

    }

}
