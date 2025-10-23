package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Procedimentos;

public interface ProcedimentosRepository extends JpaRepository<Procedimentos, Long> {

}
