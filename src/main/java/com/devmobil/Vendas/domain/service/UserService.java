package com.devmobil.Vendas.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmobil.Vendas.domain.dto.UserDTO;
import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.domain.repository.UserRepository;
import com.devmobil.Vendas.exception.PasswordInvalidException;
import com.devmobil.Vendas.resource.BaseService;

@Service
public class UserService  extends BaseService<User, UserDTO> implements UserDetailsService {

	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository repository;
	
	@Override
	@Transactional
	public User store(UserDTO dto) {
		User user = new User();
		user.setAdmin(dto.getAdmin());
		user.setName(dto.getName());
		user.setUsername(dto.getUsername());
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setCreatedAt(LocalDateTime.now());
		return repository.save(user);
	}  
	
	public UserDetails auth (User user) {
		UserDetails userAuth =  loadUserByUsername(user.getUsername());
		boolean matches = encoder.matches(user.getPassword(), userAuth.getPassword());
		if(matches) {
			return userAuth;
		}
		throw  new PasswordInvalidException();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username)
					.orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado."));
		
		String[] roles = user.getAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};
		
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(roles)
				.build();
				
	}

}
