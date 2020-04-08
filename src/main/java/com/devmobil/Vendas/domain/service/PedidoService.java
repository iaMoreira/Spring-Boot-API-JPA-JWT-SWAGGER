package com.devmobil.Vendas.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmobil.Vendas.domain.dto.ItemPedidoDto;
import com.devmobil.Vendas.domain.dto.PedidoDto;
import com.devmobil.Vendas.domain.entity.Cliente;
import com.devmobil.Vendas.domain.entity.ItemPedido;
import com.devmobil.Vendas.domain.entity.Pedido;
import com.devmobil.Vendas.domain.entity.Produto;
import com.devmobil.Vendas.domain.repository.ClienteRepository;
import com.devmobil.Vendas.domain.repository.ItemPedidoRepository;
import com.devmobil.Vendas.domain.repository.PedidoRepository;
import com.devmobil.Vendas.domain.repository.ProdutoRepository;
import com.devmobil.Vendas.exception.RegraNegocioException;

@Service
public class PedidoService implements IPedidoService{
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Override
	@Transactional
	public Pedido salvar(PedidoDto dto) {
		long idClient = dto.getCliente();
		Cliente cliente = clienteRepository
			.findById(idClient)
			.orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));
		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		
		List<ItemPedido> itensPedido = this.converterItens(pedido, dto.getItems());
		
		repository.save(pedido);
		itemPedidoRepository.saveAll(itensPedido);
		pedido.setItens(itensPedido);
		return pedido;
	}
	
	private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDto> itens) {
		if(itens.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
		}
		
		
		return itens
				.stream()
				.map(dto -> {
					long idProduto = dto.getProduto();
					Produto produto	= produtoRepository
							.findById(idProduto)
							.orElseThrow( () ->  new RegraNegocioException("Código de proudo inválido: " + idProduto));
					
					ItemPedido itemPedido = new ItemPedido();
					itemPedido.setQuantidade(dto.getQuantidade());
					itemPedido.setPedido(pedido);
					itemPedido.setProduto(produto);
					
					return itemPedido;
					}).collect(Collectors.toList());
		
	}
	
}
