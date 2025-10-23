package br.com.serratec.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.exception.EnumExcepeciton;

public enum Categoria {
	SUV, SEDAN, HATCH, PICAPE, CONVERSIVEL;
	
	@JsonCreator
	public static Categoria verificaCategoria(String value) {
		for (Categoria c: Categoria.values()) {
			if (c.name().equals(value)) {
				return c;
			}
		}
		throw new EnumExcepeciton("Categoria Invalida");
	}
}
