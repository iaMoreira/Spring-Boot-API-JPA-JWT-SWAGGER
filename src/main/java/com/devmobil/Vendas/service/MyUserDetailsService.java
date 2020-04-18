package com.devmobil.Vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.domain.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository repository;
	
	@Transactional
	public User save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repository.save(user);
	}  
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username)
					.orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado."));
		
		String[] roles = user.getAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};
		
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.roles(roles)
				.build();
				
	}

}
