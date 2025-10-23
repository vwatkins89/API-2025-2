package br.com.serratec.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Funcionario;
import br.com.serratec.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService service;
	
	@GetMapping
	public List<Funcionario> listar() {
		return service.listar();
	}
	
	@GetMapping("/paginacao")
	public Page<Funcionario> listar(@PageableDefault(		
			page = 0, size = 10, sort = "dataNascimento", 
			direction = Direction.DESC)  Pageable pageable) {
		
		return service.listarPorPagina(pageable);
	}
	
	@GetMapping("/faixaSalarial")
	public ResponseEntity<Page<Funcionario>> buscarPorFaixaSalarial(@RequestParam(defaultValue = "0") Double valorMinimo, @RequestParam(defaultValue = "10000") Double valorMaximo, Pageable pageable) {
		return ResponseEntity.ok(service.buscarPorFaixaSalarial(valorMinimo, valorMaximo, pageable));
		
	}
	
	@GetMapping("/buscarPorNome")
	public ResponseEntity<Page<Funcionario>> buscarPorNome(@RequestParam(defaultValue = "") String parteNome, Pageable pageable) {
		return ResponseEntity.ok(service.buscarPorNome(parteNome, pageable));
	}
	
	@GetMapping("/buscarPorData")
	public ResponseEntity<Page<Funcionario>> buscarPorData(@RequestParam String paramDataNascimento, Pageable pageable) {
		LocalDate dataNascimento = LocalDate.parse(paramDataNascimento,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return ResponseEntity.ok(service.buscarPorData(dataNascimento, pageable));
	}

}
