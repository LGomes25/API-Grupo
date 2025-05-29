package org.serratec.backend.repository;

import java.util.Optional;

import org.serratec.backend.entity.Conta;
import org.serratec.backend.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

	Optional<Transacao> findByContaOrigem(Conta contaOrigem);
	
}
