package com.example.lojagames.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100)
	@NotBlank(message = "O atributo 'Nome' é obrigatório!")
	@Size(min = 3, max = 100, message = "O atributo 'Nome' deve ter no mínimo 3 e no máximo 100 caracteres!")
	private String nome;
	
	@Column(length = 255)
	@NotBlank(message = "O atributo de link da capa é obrigatório!")
	@Size(min = 10, max = 255, message = "O atributo de link da capa deve ter no mínimo 10 e no máximo 255 caracteres!")
	private String linkCapa;
	
	@DecimalMin(value = "10.00", message = "O atributo 'preço' deve ter no mínimo valor 10.00!")
	@Digits(integer = 3, fraction = 2, message = "O atributo 'preço' pode ter no máximo 3 digitos inteiros!")
	private BigDecimal preco;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLinkCapa() {
		return linkCapa;
	}

	public void setLinkCapa(String linkCapa) {
		this.linkCapa = linkCapa;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
