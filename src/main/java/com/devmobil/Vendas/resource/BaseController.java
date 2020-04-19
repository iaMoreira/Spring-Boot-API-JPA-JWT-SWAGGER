package com.devmobil.Vendas.resource;

import java.lang.reflect.ParameterizedType;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


public class BaseController<E extends BaseEntity, D extends BaseDTO<E>, S extends BaseService<E, D> >  {
	  
	@Autowired
	private S service;
	   

    @GetMapping
	@ApiOperation("Listar todos itens")
	public Page<E> index(@PageableDefault Pageable pageable, E filter){
		return service.findAll(pageable, filter);	
	}
	    
	@PostMapping
	@Transactional
	@ApiOperation("Salvar item")
	@ApiResponses({@ApiResponse(code = 201, message = "Item cadastrado	")})
	public ResponseEntity<E> store(@Valid @RequestBody D dto) {
		E entity = service.store(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}
	
	@PutMapping(value = "{id}")
	@ApiOperation("Atualizar item")
	public ResponseEntity<E> update(@PathVariable(value = "id") @ApiParam("Id do item") Long id, @RequestBody @Valid D dto) {
		E entity = service.update(id, dto);
		if(entity != null) {
			return ResponseEntity.ok(entity);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, getGenericName()+" não encontrado(a)!");
	}

	@GetMapping(value = "{id}")
	@ApiOperation("Detalhar item")
	public ResponseEntity<E> show(@PathVariable(value = "id") @ApiParam("Id do item") Long id) {
		E entity = service.getOne(id);
		if(entity != null) {
			return ResponseEntity.ok(entity);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, getGenericName()+" não encontrado(a)!");
	}
		
	@DeleteMapping(value = "{id}")
	@Transactional
	@ApiOperation("Deletar item")
	public ResponseEntity<?> delete(@PathVariable(value = "id") @ApiParam("Id do item") Long id) {
		if(service.destroy(id)) {
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
