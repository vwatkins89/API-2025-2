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

import br.com.serratec.entity.Medico;
import br.com.serratec.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Medico inserir(@RequestBody Medico medico) {
		return repository.save(medico);
	}
	
	@GetMapping
	public List<Medico> listar(){
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Medico> buscar(@PathVariable Long id){
		Optional<Medico> medico = repository.findById(id);
		if (medico.isPresent()) {
			return ResponseEntity.ok(medico.get());
			
		}
		return ResponseEntity.notFound().build();
	}

}


