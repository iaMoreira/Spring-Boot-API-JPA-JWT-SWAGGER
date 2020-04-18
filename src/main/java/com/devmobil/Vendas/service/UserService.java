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
import com.devmobil.Vendas.exception.PasswordInvalidException;

@Service
public class UserService implements UserDetailsService {

	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository repository;
	
	@Transactional
	public User save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
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
