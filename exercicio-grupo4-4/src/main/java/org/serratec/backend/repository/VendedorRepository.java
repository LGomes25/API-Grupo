package org.serratec.backend.repository;

import java.util.Optional;

import org.serratec.backend.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{

	Optional<Vendedor> findByNome(String nome);
	
}
