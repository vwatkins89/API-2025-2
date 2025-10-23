package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Gerente;
import br.com.serratec.repository.GerenteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {
	@Autowired
	private GerenteRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Gerente inserir(@Valid @RequestBody Gerente gerente) {
		return repository.save(gerente);
	}
	
	@GetMapping
	public List<Gerente> listar(){
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Gerente> buscar(@PathVariable Long id){
		Optional<Gerente> gerente = repository.findById(id);
		if (gerente.isPresent()) {
			return ResponseEntity.ok(gerente.get());
			
		}
		return ResponseEntity.notFound().build();
	}

}


