package org.serratec.bakcend.repository;

import org.serratec.bakcend.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
