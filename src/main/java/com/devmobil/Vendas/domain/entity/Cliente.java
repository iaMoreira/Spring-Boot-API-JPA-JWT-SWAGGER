package com.devmobil.Vendas.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cliente")
public class Cliente extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "nome", length = 100)
	@NotEmpty(message = "O campo nome é obrigátorio.")
	private String nome;
	
	@Column(name = "email", length = 100, unique = true)
	@Email(message = "Insira o email válido.")
    private String email;
	
    @JsonIgnore // funciona como um hidden e esconde o campo quando é rederizado em Json 
	@Column(name = "senha", length = 100)
    @NotEmpty(message = "O campo senha é obrigátorio.")
    private String senha;
	
    @JsonIgnore
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private Set<Pedido> pedidos;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Set<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public Cliente(long id, String nome, String email, String senha, Set<Pedido> pedidos) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.pedidos = pedidos;
	}
	
	public Cliente() {
		
	}	
}
