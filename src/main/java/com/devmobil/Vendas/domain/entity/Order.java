package com.devmobil.Vendas.domain.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.devmobil.Vendas.domain.enums.StatusOrder;
import com.devmobil.Vendas.resource.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "total", precision = 20, scale= 2)
	private BigDecimal total;
	
	@OneToMany
	@JoinColumn(name = "order_id")
	private List<ItemOrder> itens;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusOrder status;
	
	
	
}
