package org.serratec.backend.service;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.entity.Conta;
import org.serratec.backend.exception.ContaException;
import org.serratec.backend.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository repository;
	
	//listar todas as contas
	public List<Conta> listar(){										//Lista contas 
		return repository.findAll();
	}
	
	//criar uma nova conta
	public Conta inserir(Conta conta) {									//Criar conta de acordo com cpf unico e numero conta unica
		Optional<Conta> cpf = repository.findByCpf(conta.getCpf());
		Optional<Conta> nconta = repository.findByNumeroConta(conta.getNumeroConta());
		
		if(cpf.isPresent()) {
			throw new ContaException("CPF já vinculado a uma conta existente, operação cancelada");
		}
		if(nconta.isPresent()) {
			throw new ContaException("Numero de conta já criado, operação cancelada");
		}
		return repository.save(conta);
	}
}
