package br.com.serratec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.entity.LancamentoVendas;
import br.com.serratec.repository.LancamentoRepository;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository repository;
	
	public LancamentoVendas buscar(Long id) {
		Optional<LancamentoVendas> lancamento = repository.findById(id);
		return lancamento.get();
	}
	
	public LancamentoVendas inserir(LancamentoVendas lancamento) {
		return repository.save(lancamento);
	}

}
