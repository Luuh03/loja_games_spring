package com.example.lojagames.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.lojagames.model.Produto;
import com.example.lojagames.repository.CategoriaRepository;
import com.example.lojagames.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id ) {
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok( produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/valorMaior/{preco}")
	public ResponseEntity<List<Produto>> getByValorMaior(@PathVariable BigDecimal preco) {
		return ResponseEntity.ok( produtoRepository.findAllByPrecoGreaterThan(preco));
	}
	
	@GetMapping("/valorMenor/{preco}")
	public ResponseEntity<List<Produto>> getByValorMenor(@PathVariable BigDecimal preco) {
		return ResponseEntity.ok( produtoRepository.findAllByPrecoLessThan(preco));
	}
	
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		
		if(categoriaRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A categoria não existe!", null);
	}
	
	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {

		if(produto.getId() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		if(produtoRepository.existsById(produto.getId())) {
			
			if(categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.ok(produtoRepository.save(produto));
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A categoria não existe!", null);
		}
		
		return ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {

		Optional<Produto> produto = produtoRepository.findById(id);
		
		if(produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		produtoRepository.deleteById(id);
	}

}
