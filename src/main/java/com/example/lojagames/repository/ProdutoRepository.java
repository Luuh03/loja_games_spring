package com.example.lojagames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojagames.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	List<Produto> findAllByNomeContainingIgnoreCase(String nome);

}
