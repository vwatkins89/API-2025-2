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

import br.com.serratec.entity.Exame;
import br.com.serratec.repository.ExameRepository;

@RestController
@RequestMapping("/exames")
public class ExameController {
	@Autowired
	private ExameRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Exame inserir(@RequestBody Exame exame) {
		return repository.save(exame);
	}
	
	@GetMapping
	public List<Exame> listar(){
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Exame> buscar(@PathVariable Long id){
		Optional<Exame> exame = repository.findById(id);
		if (exame.isPresent()) {
			return ResponseEntity.ok(exame.get());
			
		}
		return ResponseEntity.notFound().build();
	}

}


