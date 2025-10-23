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

import br.com.serratec.entity.Procedimentos;
import br.com.serratec.repository.ProcedimentosRepository;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentosController {
	@Autowired
	private ProcedimentosRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Procedimentos inserir(@RequestBody Procedimentos procedimentos) {
		return repository.save(procedimentos);
	}
	
	@GetMapping
	public List<Procedimentos> listar(){
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Procedimentos> buscar(@PathVariable Long id){
		Optional<Procedimentos> procedimentos = repository.findById(id);
		if (procedimentos.isPresent()) {
			return ResponseEntity.ok(procedimentos.get());
			
		}
		return ResponseEntity.notFound().build();
	}

}


