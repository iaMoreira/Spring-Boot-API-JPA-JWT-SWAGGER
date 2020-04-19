package com.devmobil.Vendas.domain.dto;

import java.math.BigDecimal;
import java.util.List;

import com.devmobil.Vendas.domain.entity.Order;
import com.devmobil.Vendas.domain.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoOrderDTO {
	
	private Long id;
	
	private User user;
	
	private BigDecimal total;
	private String status;
	private List<InfoItemOrderDTO> itens;
	
	
	public static InfoOrderDTO converter (Order order) {
		InfoOrderDTO infoOrderDTO = new InfoOrderDTO();
		infoOrderDTO.setId(order.getId());
		infoOrderDTO.setUser(order.getUser());
		infoOrderDTO.setTotal(order.getTotal());
		infoOrderDTO.setStatus(order.getStatus().name());
//		infoOrderDTO.set(order.getDataOrder().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
		infoOrderDTO.setItens(InfoItemOrderDTO.collection(order.getItens()));
		return infoOrderDTO;
	}
	
	
		

}
