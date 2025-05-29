package org.serratec.backend.service;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.entity.Conta;
import org.serratec.backend.entity.Transacao;
import org.serratec.backend.exception.ContaException;
import org.serratec.backend.repository.ContaRepository;
import org.serratec.backend.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transRepo;
	
	@Autowired
	private ContaRepository contaRepo;
	
	//listar todas as transacoes
	public List<Transacao> listar(){
		return transRepo.findAll();
	}
	
	@Transactional
	public Transacao realizarPix(Transacao transacao) {
		Optional<Conta> cOrigem = contaRepo.findById(transacao.
														getContaOrigem().
														getId()
														);
		
		Optional<Conta> cDestino = contaRepo.findById(transacao.
														getContaDestino().
														getId()
														);
		
		if(!cOrigem.isPresent()) {
			throw new ContaException("Numero de conta Origem não existe, operação cancelada");
		}
		if(!cDestino.isPresent()) {
			throw new ContaException("Numero de conta Destino não existe, operação cancelada");
		}
		if(cOrigem.get().getSaldo().compareTo(transacao.getValor()) <0) {
			throw new ContaException("Saldo insuficiente na conta Origem, operação cancelada");
		}
		
		cOrigem.get().setSaldo(cOrigem.get().getSaldo().subtract(transacao.getValor()));
		cDestino.get().setSaldo(cDestino.get().getSaldo().add(transacao.getValor()));
		
		contaRepo.save(cOrigem.get());
		contaRepo.save(cDestino.get());
		
		return transRepo.save(transacao);
	}
	
	
}
