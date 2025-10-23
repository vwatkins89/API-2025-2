package br.com.serratec.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.LancamentoVendasRequestDTO;
import br.com.serratec.dto.LancamentoVendasResponseDTO;
import br.com.serratec.entity.LancamentoVendas;
import br.com.serratec.entity.Vendedor;
import br.com.serratec.repository.LancamentoRepository;
import br.com.serratec.repository.VendedorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class LancamentoVendasService {
	@Autowired
    private LancamentoRepository repository;
    //faltando busca por id e deleta
    @Autowired
    private VendedorRepository vr;
    
    //lista
    public LancamentoVendasResponseDTO listarPorId(Long id) {
        LancamentoVendas lv = repository.findById(id).
        		orElseThrow(()-> new EntityNotFoundException("Lançamento de venda não encontrado no ID:" + id));
        
        return new LancamentoVendasResponseDTO(
        		lv.getData(),
        		lv.getValor(),
        		lv.getVendedor().getNome()
        	);
    }
    
    
    //insere
    @Transactional //faz com que seja concomitante, se não salvar o de cima, a de baixa não salva
	public LancamentoVendasResponseDTO inserirLancamento(LancamentoVendasRequestDTO lvrq) throws EntityNotFoundException{
			Vendedor vendedor = vr.findById(lvrq.getVendedor_id())
					.orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado: " + lvrq.getVendedor_id()));
    	
    		var lv = new LancamentoVendas();
			lv.setData(lvrq.getData());
			lv.setValor(lvrq.getValor());
			lv.setVendedor(vendedor);
			//salvo no banco
			repository.save(lv);
			var LancamentoResponse = new LancamentoVendasResponseDTO(lv.getData(),lv.getValor(),lv.getVendedor().getNome());
			//retorna um usuario, e transforma em response dto
			return LancamentoResponse;
	}
}