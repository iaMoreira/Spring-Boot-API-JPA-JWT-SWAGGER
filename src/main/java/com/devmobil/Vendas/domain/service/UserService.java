package com.devmobil.Vendas.domain.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmobil.Vendas.domain.dto.ChangePasswordDTO;
import com.devmobil.Vendas.domain.dto.TokenDTO;
import com.devmobil.Vendas.domain.dto.UserDTO;
import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.domain.repository.UserRepository;
import com.devmobil.Vendas.exception.PasswordInvalidException;
import com.devmobil.Vendas.resource.BaseService;
import com.devmobil.Vendas.security.JwtService;

@Service
public class UserService  extends BaseService<User, UserDTO> implements UserDetailsService {

	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepository repository;
	
	@Override
	@Transactional
	public User store(UserDTO dto) {
		User user = new User();
		user.setAdmin(dto.getAdmin());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setCreatedAt(LocalDateTime.now());
		return repository.save(user);
	}  
	
	public TokenDTO login(UserDTO credentials) {
		User user = User.builder()
				.email(credentials.getEmail())
				.password(credentials.getPassword()).build();
		
		this.auth(user);
		String token = jwtService.createToken(user);
		Optional<User> optional = repository.findByEmail(credentials.getEmail());
		
		TokenDTO dto = new TokenDTO(optional.get().getId(), credentials.getEmail(), optional.get().getName(), token);
		return dto;
	}
	
	public TokenDTO userAuth() {
		User user = this.currentUser();
    	return new TokenDTO(user.getId(), user.getEmail(), user.getName(), jwtService.createToken(user));
	}
	
	public UserDetails auth (User user) {
		UserDetails userAuth =  loadUserByUsername(user.getEmail());
		boolean matches = encoder.matches(user.getPassword(), userAuth.getPassword());
		if(matches) {
			return userAuth;
		}
		throw  new PasswordInvalidException();
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.findByEmail(email)
					.orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado."));
		
		String[] roles = user.getAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};
		
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.roles(roles)
				.build();
				
	}
	
	public Boolean changePassword(ChangePasswordDTO dto) {
		User currenUser = this.currentUser();
		boolean matches = encoder.matches(dto.getCurrentPassword(), currenUser.getPassword());
		currenUser.setPassword(encoder.encode(dto.getNewPassword()));
		if(matches) {
			repository.save(currenUser);
			return true;
		}
		return false;
	}
	
	public User currentUser() {
    	org.springframework.security.core.userdetails.User username =  (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                .getAuthentication()	
                .getPrincipal();
    	Optional<User> optional = repository.findByEmail(username.getUsername());
    	
    	User user = optional.get();
    	return user;
	}

	
}
