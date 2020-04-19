package com.devmobil.Vendas.domain.controller;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.server.ResponseStatusException;

import com.devmobil.Vendas.resource.BaseRepository;
import com.devmobil.Vendas.resource.BaseEntity;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


public class OldBaseController<E extends BaseEntity> {
	
	@Autowired
	protected BaseRepository<E> repository;
	
	@GetMapping
	@ApiOperation("Listar todos itens")
	@ApiResponses({@ApiResponse(code = 200, message = "Itens encontrados")})
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
	@ApiOperation("Salvar item")
	@Transactional
	public ResponseEntity<E> store(@RequestBody @Valid E entity) {
		E newEntity = repository.save(entity); 		
		return ResponseEntity.status(HttpStatus.CREATED).body(newEntity);
	}

	@PutMapping(value = "{id}")
	@ApiOperation("Atualizar item")
	public ResponseEntity<E> update(@PathVariable(value = "id") @ApiParam("Id do item") Long id, @RequestBody @Valid E entity) {
		Optional<E> optional = repository.findById(id);
		if(optional.isPresent()) {
			entity.setId(id);
			E newEntity = repository.save(entity); 
			return ResponseEntity.ok(newEntity);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, getGenericName()+".id = "+ id +" não encontrado(a)!");
	}

	@GetMapping(value = "{id}")
	@ApiOperation("Detalhar item")
	public ResponseEntity<E> show(@PathVariable(value = "id") @ApiParam("Id do item") Long id) {
		Optional<E> entity = repository.findById(id);
		if(entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, getGenericName()+".id = "+ id +" não encontrado(a)!");
	}

	@DeleteMapping(value = "{id}")
	@Transactional
	@ApiOperation("Deletar item")
	public ResponseEntity<?> delete(@PathVariable(value = "id") @ApiParam("Id do item") Long id) {
		Optional<E> optional = repository.findById(id);
		if(optional.isPresent()) {
			repository.deleteById(id);	
			return ResponseEntity.noContent().build();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, getGenericName()+".id = "+ id +" não encontrado(a)!");
	}
	
	@SuppressWarnings("unchecked")
	protected String getGenericName()
    {
        return ((Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getTypeName().split("\\.")[5];
    }
	
}
