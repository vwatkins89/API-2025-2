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

import br.com.serratec.entity.Consulta;
import br.com.serratec.repository.ConsultaRepository;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
	@Autowired
	private ConsultaRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Consulta inserir(@RequestBody Consulta consulta) {
		return repository.save(consulta);
	}
	
	@GetMapping
	public List<Consulta> listar(){
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Consulta> buscar(@PathVariable Long id){
		Optional<Consulta> consulta = repository.findById(id);
		if (consulta.isPresent()) {
			return ResponseEntity.ok(consulta.get());
			
		}
		return ResponseEntity.notFound().build();
	}

}


