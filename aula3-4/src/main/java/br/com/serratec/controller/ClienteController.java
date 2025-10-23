package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;

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

import br.com.serratec.entity.Cliente;
import br.com.serratec.repository.ClienteRepository;
import jakarta.validation.Valid;

@RestController //Criar o controlador para conversar com o Postman
@RequestMapping("/clientes") //Criar o recurso /clientes na URL
public class ClienteController {
	@Autowired //faz a injeção de dependência - cria o objeto
	private ClienteRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente inserir(@Valid @RequestBody Cliente cliente) {
		return repository.save(cliente);

	}

	@PostMapping("/varios")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Cliente> inserirVariosClientes(@Valid @RequestBody List<Cliente> clientes) {
		return repository.saveAll(clientes);
	}

	@GetMapping
	public List<Cliente> listar() {
		return repository.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> apagar(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id,@Valid @RequestBody Cliente cliente) {
		Optional<Cliente> c = repository.findById(id);
		
		if (c.isPresent()) {
			cliente.setId(id);
			repository.save(cliente);
			return ResponseEntity.ok(repository.save(cliente));
		}
			return ResponseEntity.notFound().build();
	}

}
