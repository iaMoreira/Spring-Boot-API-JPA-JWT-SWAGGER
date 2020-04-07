package com.devmobil.Vendas.domain.controller;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.server.ResponseStatusException;

import com.devmobil.Vendas.domain.entity.BaseEntity;
import com.devmobil.Vendas.domain.repository.BaseRepository;


public class BaseController<E extends BaseEntity, R> {
	
	@Autowired
	protected BaseRepository<E> repository;
	
	@GetMapping
	public Page<E> index(@PageableDefault Pageable pageable, E filter){
		 ExampleMatcher matcher = ExampleMatcher
                 .matching()
                 .withIgnoreNullValues()
                 .withIgnoreCase()
                 .withIgnorePaths("id")
                 .withStringMatcher(
                         ExampleMatcher.StringMatcher.CONTAINING ); // https://www.logicbig.com/tutorials/spring-framework/spring-data/query-example-matchers.html

		Example<E> example = Example.of(filter, matcher);
		return repository.findAll(example, pageable);	
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
			entity.setId(id);
			E newEntity = repository.save(entity); 
			return ResponseEntity.ok(newEntity);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, getGenericName()+" não encontrado(a)!");
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<E> show(@PathVariable(value = "id") long id) {
		Optional<E> entity = repository.findById(id);
		if(entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, getGenericName()+" não encontrado(a)!");
	}

	@DeleteMapping(value = "{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
		Optional<E> optional = repository.findById(id);
		if(optional.isPresent()) {
			repository.deleteById(id);	
			return ResponseEntity.noContent().build();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, getGenericName()+" não encontrado(a)!");
	}
	
	@SuppressWarnings("unchecked")
	protected String getGenericName()
    {
        return ((Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getTypeName().split("\\.")[5];
    }
	
}
