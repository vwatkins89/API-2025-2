package br.com.serratec.controller;

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

import br.com.serratec.entity.LancamentoVendas;
import br.com.serratec.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
	
	@Autowired
	private LancamentoService service;
	
	@GetMapping("{id}")
	public ResponseEntity<LancamentoVendas> buscar(@PathVariable Long id) {
		LancamentoVendas lancamento = service.buscar(id);
		if (lancamento !=null) {
			return ResponseEntity.ok(lancamento);
			
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LancamentoVendas inserir(@RequestBody LancamentoVendas lancamento) {
		return service.inserir(lancamento);
		
	}
}
