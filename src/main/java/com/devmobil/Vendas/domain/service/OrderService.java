package com.devmobil.Vendas.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmobil.Vendas.domain.dto.ItemPedidoDto;
import com.devmobil.Vendas.domain.dto.OrderDTO;
import com.devmobil.Vendas.domain.entity.ItemOrder;
import com.devmobil.Vendas.domain.entity.Order;
import com.devmobil.Vendas.domain.entity.Product;
import com.devmobil.Vendas.domain.entity.User;
import com.devmobil.Vendas.domain.enums.StatusOrder;
import com.devmobil.Vendas.domain.repository.ItemOrderRepository;
import com.devmobil.Vendas.domain.repository.OrderRepository;
import com.devmobil.Vendas.domain.repository.ProductRepository;
import com.devmobil.Vendas.domain.repository.UserRepository;
import com.devmobil.Vendas.exception.PedidoNaoEncontradoException;
import com.devmobil.Vendas.exception.RegraNegocioException;
import com.devmobil.Vendas.resource.BaseService;

@Service
public class OrderService extends BaseService<Order, OrderDTO> {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ItemOrderRepository itemPedidoRepository;
	
	@Override
	@Transactional
	public Order store(OrderDTO dto) {
		Long userId = dto.getUser();
		User user = userRepository
			.findById(userId)
			.orElseThrow(() -> new RegraNegocioException("Código do usuário = " + userId+ " é inválido."));
		
		Order order = new Order();
		order.setTotal(dto.getTotal());
		order.setCreatedAt(LocalDateTime.now());
		order.setStatus(StatusOrder.REALIZADO);
		order.setUser(user);
		
		List<ItemOrder> itensPedido = this.converterItens(order, dto.getItems());
		
		repository.save(order);
		itemPedidoRepository.saveAll(itensPedido);
		order.setItens(itensPedido);
		return order;
	}
	
	public Optional<Order> getPedido(Long id) {
		return repository.findByIdFetchItens(id);
	}
	
	private List<ItemOrder> converterItens(Order order, List<ItemPedidoDto> itens) {
		if(itens.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um order sem itens.");
		}
		
		
		return itens
				.stream()
				.map(dto -> {
					Long productId = dto.getProduct();
					Product product	= productRepository
							.findById(productId)
							.orElseThrow( () ->  new RegraNegocioException("Código de proudo inválido: " + productId));
					
					ItemOrder itemPedido = new ItemOrder();
					itemPedido.setAmount(dto.getAmount());
					itemPedido.setOrder(order);
					itemPedido.setProduct(product);
					itemPedido.setCreatedAt(LocalDateTime.now());
					return itemPedido;
					}).collect(Collectors.toList());
		
	}

	@Transactional
	public void atualizaStatus(Long id, StatusOrder statusPedido) {
		repository
			.findById(id)
			.map( order -> {
				order.setStatus(statusPedido);
				return repository.save(order);
			}).orElseThrow( () -> new PedidoNaoEncontradoException());
		
	}
	
}
