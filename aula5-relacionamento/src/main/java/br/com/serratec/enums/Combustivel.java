package br.com.serratec.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.exception.EnumExcepeciton;

public enum Combustivel {
	FLEX(1,"FLEX"), DIESEL(2,"DIESEL"), ELETRICO(3,"ELETRICO");
	private Integer id;
	private String tipo;
	private Combustivel(Integer id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}
	public Integer getId() {
		return id;
	}
	public String getTipo() {
		return tipo;
	}
	
	@JsonCreator
	public static Combustivel verificaCombustivel(Integer value) {
		for (Combustivel c: Combustivel.values()) {
			if (c.getId().equals(value)) {
				return c;
			}
		}
		throw new EnumExcepeciton("Combustivel Invalido");
	}


}

