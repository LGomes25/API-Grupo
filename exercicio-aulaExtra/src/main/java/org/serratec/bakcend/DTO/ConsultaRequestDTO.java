package org.serratec.bakcend.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ConsultaRequestDTO {

	@NotNull
	private LocalDate  dataConsulta;
	
	@NotNull
	private Long idPaciente;
	
	@NotNull
	private Long idMedico;
}
