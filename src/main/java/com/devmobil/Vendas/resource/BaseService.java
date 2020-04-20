package com.devmobil.Vendas.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class BaseService<E extends BaseEntity, D extends BaseDTO<E>> {
	
	@Autowired
	protected BaseRepository<E> repository;
	
	
	public Page<E> findAll(Pageable pageable, E filter) {
		ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING ); 

		Example<E> example = Example.of(filter, matcher);
		return repository.findAll(example, pageable);
	}
	
	public E getOne(Long id) {
		Optional<E> optional = repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;

	}
	
	public E store(D dto) {
		E entity = dto.getEntity();
		repository.save(entity);
		return entity;
	}
	
	public E update(Long id, D dto) {
		Optional<E> optional = repository.findById(id);
		if(optional.isPresent()) {
			E entity = dto.getEntity();
			entity.setId(id);
			E newEntity = repository.save(entity);
			return newEntity;
		}
		return null;
	}
	
	
	public Boolean destroy(Long id) {
		Optional<E> optional = repository.findById(id);
		if(optional.isPresent()) {
			repository.deleteById(id);	
			return true;
		}
		return false;
	}


}
