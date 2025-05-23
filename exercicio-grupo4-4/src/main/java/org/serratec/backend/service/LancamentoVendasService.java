package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.dto.LancamentoVendasRequestDTO;
import org.serratec.backend.dto.LancamentoVendasResponseDTO;
import org.serratec.backend.entity.LancamentoVendas;
import org.serratec.backend.entity.Vendedor;
import org.serratec.backend.exception.LancamentoException;
import org.serratec.backend.repository.LancamentoVendasRepository;
import org.serratec.backend.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoVendasService {

	@Autowired
	private LancamentoVendasRepository repo;
	
	@Autowired
	private VendedorRepository vendedorRepo;
	
	public List<LancamentoVendasResponseDTO> listar () {
		List<LancamentoVendas> vendas = repo.findAll();
		List<LancamentoVendasResponseDTO> lancaDTO = new ArrayList<>();
		for (LancamentoVendas lv: vendas) {
			lancaDTO.add(new LancamentoVendasResponseDTO(
					lv.getDataVenda(),
					lv.getValorVenda(),
					lv.getVendedor().getNome())
					);
		}
		return lancaDTO;
	}
	
	public LancamentoVendasResponseDTO listarPorId(Long id){			//capturado no endpoint
		Optional<LancamentoVendas> vendas = repo.findById(id);
		if(!vendas.isPresent()) {
			throw new LancamentoException("Venda com ID "+ id +" não encontrada");
		}
		LancamentoVendas lv = vendas.get();
		return new LancamentoVendasResponseDTO(
						lv.getDataVenda(),
						lv.getValorVenda(),
						lv.getVendedor().getNome()
						);
	}
	
	public LancamentoVendasResponseDTO inserir(LancamentoVendasRequestDTO dto) {

		Optional<Vendedor> vendedor = vendedorRepo.findByNome(dto.getNomeVendedor());

		if (!vendedor.isPresent()) {
		    throw new LancamentoException("Vendedor não encontrado. Venda cancelada");
		}

		LancamentoVendas lv = new LancamentoVendas();
	    lv.setValorVenda(dto.getValorVenda());
	    lv.setVendedor(vendedor.get());

		lv = repo.save(lv);
        return new LancamentoVendasResponseDTO(
        				lv.getDataVenda(),
        				lv.getValorVenda(), 
        				lv.getVendedor().getNome()
        				);
	}

}
