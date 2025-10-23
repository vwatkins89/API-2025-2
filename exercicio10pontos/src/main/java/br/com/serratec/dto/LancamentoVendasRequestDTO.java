package br.com.serratec.dto;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class LancamentoVendasRequestDTO {
    
 
    private LocalDate data;
    private Double valor;
    private Long vendedor_id;

    public LancamentoVendasRequestDTO() {
    }

    public LancamentoVendasRequestDTO(LocalDate data, Double valor, Long vendedor_id) {
        super();
        this.data = data;
        this.valor = valor;
        this.vendedor_id = vendedor_id;
    }

    
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }


    public Long getVendedor_id() {
        return vendedor_id;
    }

    public void setVendedor_id(Long vendedor_id) {
        this.vendedor_id = vendedor_id;
    }

}