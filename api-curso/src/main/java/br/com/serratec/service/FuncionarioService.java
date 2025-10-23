package br.com.serratec.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.entity.Funcionario;
import br.com.serratec.repository.FuncionarioRepository;


@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository repository;
	
	
	public List<Funcionario> listar() {
		return repository.findAll();
	}
	
	public Page<Funcionario> listarPorPagina(Pageable pageable) {
		
		return repository.findAll(pageable);
		
	}
	
	public Page<Funcionario> buscarPorFaixaSalarial(Double valorMinimo, Double valorMaximo, Pageable pageable) {
		return repository.findBySalarioBetween(valorMinimo, valorMaximo, pageable);
	}
	
	public Page<Funcionario> buscarPorNome(String parteNome, Pageable pageable) {
		return repository.findByNomeContaining(parteNome, pageable);
	}
	
	public Page<Funcionario> buscarPorData(LocalDate dataNascimento, Pageable pageable) {
		return repository.findByDataNascimentoGreaterThan(dataNascimento, pageable);
	}
}
