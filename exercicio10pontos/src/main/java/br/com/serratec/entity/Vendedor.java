package br.com.serratec.entity;

import java.util.List;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public class Vendedor {	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "O email é obrigatporio")
	@Email(message = "Formato inválido")
	private String email;
	
	@NotNull(message = "O salário é obrigatório")
	@Min(value = 1518, message = "O salário não pode ser inferior ao salário mínimo")
	private Double salario;
	
	// Relacionamento: Um vendedor possui vários lançamentos 
    @OneToMany(mappedBy = "vendedor")
    
    private List<LancamentoVendas> lancamentos;
	
	
	public Vendedor() {
	}

	public Vendedor(Long id, @NotBlank(message = "O nome é obrigatório") String nome,
			@NotBlank(message = "O email é obrigatorio") @Email(message = "Formato inválido") String email,
			@NotNull(message = "O salário é obrigatório") @Min(value = 1518, message = "O salário não pode ser inferior ao salário mínimo") Double salario) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.salario = salario;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

}
