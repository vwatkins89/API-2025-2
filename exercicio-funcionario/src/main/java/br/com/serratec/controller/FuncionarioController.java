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

import br.com.serratec.entity.Funcionario;
import br.com.serratec.repository.FuncionarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	@Autowired
	private FuncionarioRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario inserir(@Valid @RequestBody Funcionario funcionario) {
		return repository.save(funcionario);
	}
	
	@GetMapping
	public List<Funcionario> listar(){
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Funcionario> buscar(@PathVariable Long id){
		Optional<Funcionario> funcionario = repository.findById(id);
		if (funcionario.isPresent()) {
			return ResponseEntity.ok(funcionario.get());
			
		}
		return ResponseEntity.notFound().build();
	}

}


