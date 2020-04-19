package com.devmobil.Vendas.domain.dto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.devmobil.Vendas.domain.entity.ItemOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoItemOrderDTO {
	
	private String name;
	
	private BigDecimal price;
	
	private Integer amount;
	
	public static List<InfoItemOrderDTO> collection(List<ItemOrder> itens){
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map( 
			item -> { 
				InfoItemOrderDTO info = new InfoItemOrderDTO();
				info.setName(item.getProduct().getName());
				info.setPrice(item.getProduct().getPrice());
				info.setAmount(item.getAmount());
				return info;
			}).collect(Collectors.toList());
	}
	
	

}
