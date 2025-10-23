package br.com.serratec.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("AUTONOMO")
public class VendedorAutonomo extends Vendedor{
	
	private Double comissao;
	
	public VendedorAutonomo() {
	}
	
	public VendedorAutonomo(Double comissao) {
		super();
		this.comissao = comissao;
	}
	

	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	
}
