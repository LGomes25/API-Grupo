package org.serratec.backend.repository;

import java.util.Optional;

import org.serratec.backend.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContaRepository extends JpaRepository<Conta,Long>{

	Optional<Conta> findByCpf(String cpf);					//buscar cpf no BD
	Optional<Conta> findByNumeroConta(String numeroConta);	//buscar conta pelo numero no BD

}
