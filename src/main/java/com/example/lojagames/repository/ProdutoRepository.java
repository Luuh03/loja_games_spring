package com.example.lojagames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojagames.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	List<Produto> findAllByNomeContainingIgnoreCase(String nome);

	List<Produto> findAllByPrecoLessThan(BigDecimal valor);
	
	List<Produto> findAllByPrecoGreaterThan(BigDecimal valor);
}
