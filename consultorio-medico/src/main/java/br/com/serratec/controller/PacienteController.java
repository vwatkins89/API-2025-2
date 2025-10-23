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

import br.com.serratec.entity.Paciente;
import br.com.serratec.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	@Autowired
	private PacienteRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Paciente inserir(@RequestBody Paciente paciente) {
		return repository.save(paciente);
	}
	
	@GetMapping
	public List<Paciente> listar(){
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Paciente> buscar(@PathVariable Long id){
		Optional<Paciente> paciente = repository.findById(id);
		if (paciente.isPresent()) {
			return ResponseEntity.ok(paciente.get());
			
		}
		return ResponseEntity.notFound().build();
	}

}


