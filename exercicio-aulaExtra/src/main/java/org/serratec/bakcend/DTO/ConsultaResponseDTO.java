package org.serratec.bakcend.DTO;

import java.time.LocalDate;
import java.util.List;

public record ConsultaResponseDTO (LocalDate dataConsulta, String nomePaciente, List<ProcedimentosResponseDTO> procedimentosDTO, Double totalGeral){
	
}


