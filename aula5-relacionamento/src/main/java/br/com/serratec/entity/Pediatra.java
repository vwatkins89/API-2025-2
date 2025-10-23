package br.com.serratec.entity;

import jakarta.persistence.Entity;

@Entity
public class Pediatra extends Medico {
	
	private Boolean aplicaVacina;

	public Boolean getAplicaVacina() {
		return aplicaVacina;
	}

	public void setAplicaVacina(Boolean aplicaVacina) {
		this.aplicaVacina = aplicaVacina;
	}
	
	

}
