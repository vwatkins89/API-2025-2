package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Proprietario;
import br.com.serratec.repository.ProprietarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {
	@Autowired
	private ProprietarioRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Proprietario inserir(@Valid @RequestBody Proprietario proprietario) {
		return repository.save(proprietario);
	}
	
	@GetMapping
	public List<Proprietario> listar(){
		return repository.findAll();
	}

}


