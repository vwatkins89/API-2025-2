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

import br.com.serratec.dto.UsuarioRequestDTO;
import br.com.serratec.dto.UsuarioResponseDTO;
import br.com.serratec.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public List<UsuarioResponseDTO> listar() {
		return service.listar();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioResponseDTO inserir(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
//		usuarioRequestDTO = service.inserir(usuarioRequestDTO);
//		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
//				.path("/{id}").buildAndExpand(usuario.getId()).toUri();
//		return ResponseEntity.created(uri).body(usuario);
		
		return service.inserir(usuarioRequestDTO);
	}

}
