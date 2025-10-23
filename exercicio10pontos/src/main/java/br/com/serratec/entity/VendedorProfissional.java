package br.com.serratec.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PROFISSIONAL")
public class VendedorProfissional extends Vendedor {
	
		private String cnpj;
		
		public VendedorProfissional() {
		}

		public VendedorProfissional(String cnpj) {
			super();
			this.cnpj = cnpj;
		}

		public String getCnpj() {
			return cnpj;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
		
		
}
