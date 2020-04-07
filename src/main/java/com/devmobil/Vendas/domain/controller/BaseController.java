package com.devmobil.Vendas.domain.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.devmobil.Vendas.domain.entity.BaseEntity;
import com.devmobil.Vendas.domain.repository.BaseRepository;


public class BaseController<E extends BaseEntity, R> {
	
	@Autowired
	protected BaseRepository<E> repository;
	
	@GetMapping
	public Page<E> index(@PageableDefault Pageable pageable){
		return repository.findAll(pageable);	
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<E> store(@RequestBody E entity, UriComponentsBuilder uriComponentsBuilder ) {
		E newEntity = repository.save(entity); 
		
		URI uri = uriComponentsBuilder.path("/").buildAndExpand().toUri();
		return ResponseEntity.created(uri).body(newEntity);
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<E> update(@PathVariable(value = "id") long id, @RequestBody E entity) {
		Optional<E> optional = repository.findById(id);
		if(optional.isPresent()) {
			E newEntity = repository.save(entity); 
			return ResponseEntity.ok(newEntity);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<E> show(@PathVariable(value = "id") long id) {
		Optional<E> entity = repository.findById(id);
		if(entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping(value = "{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
		Optional<E> optional = repository.findById(id);
		if(optional.isPresent()) {
			repository.deleteById(id);	
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
}
