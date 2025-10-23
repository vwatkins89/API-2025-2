package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Produto;
import br.com.serratec.repository.ProdutoRepository;

@RestController //Criar o controlador para conversar com o Postman
@RequestMapping("/produtos") //Criar o recurso /produtos na URL
public class ProdutoController {
	@Autowired //faz a injeção de dependência - cria o objeto
	private ProdutoRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto inserir(@RequestBody Produto produto) {
		return repository.save(produto);

	}

	@PostMapping("/varios")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Produto> inserirVariosProdutos(@RequestBody List<Produto> produtos) {
		return repository.saveAll(produtos);
	}

	@GetMapping
	public List<Produto> listar() {
		return repository.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Produto> buscar(@PathVariable UUID id) {
		Optional<Produto> produto = repository.findById(id);
		if (produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> apagar(@PathVariable UUID id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable UUID id, @RequestBody Produto produto) {
		Optional<Produto> p = repository.findById(id);
		
		if (p.isPresent()) {
			produto.setId(id);
			repository.save(produto);
			return ResponseEntity.ok(repository.save(produto));
		}
			return ResponseEntity.notFound().build();
	}

}
