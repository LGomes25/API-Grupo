package org.serratec.bakcend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.bakcend.DTO.ConsultaRequestDTO;
import org.serratec.bakcend.DTO.ConsultaResponseDTO;
import org.serratec.bakcend.DTO.ProcedimentosResponseDTO;
import org.serratec.bakcend.entity.Consulta;
import org.serratec.bakcend.entity.Medico;
import org.serratec.bakcend.entity.Paciente;
import org.serratec.bakcend.entity.Procedimentos;
import org.serratec.bakcend.exception.ConsultaException;
import org.serratec.bakcend.repository.ConsultaRepository;
import org.serratec.bakcend.repository.MedicoRepository;
import org.serratec.bakcend.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository repoConsulta;
	
	@Autowired
	private PacienteRepository repoPaciente;
	
	@Autowired
	private MedicoRepository repoMedico;

	public List<ConsultaResponseDTO> listar (){
		List<Consulta> consultas = repoConsulta.findAll();
		List<ConsultaResponseDTO> consultaDtos = new ArrayList<>();
		
		for (Consulta cnst : consultas) {
			List<ProcedimentosResponseDTO> procDto = new ArrayList<>();
			Double totalGeral = 0.;
			
			for (Procedimentos proc : cnst.getProcedimentos()) {
				
				Double subtotal = proc.getQuantidade() * proc.getValorProcedimento();
				totalGeral += subtotal;
				
				procDto.add(new ProcedimentosResponseDTO(proc.getExame().getTipo(), 
														proc.getQuantidade(), 
														proc.getValorProcedimento(), 
														subtotal));
			}
			consultaDtos.add(new ConsultaResponseDTO(cnst.getDataConsulta(),
													cnst.getPaciente().getNome(), 
													procDto, 
													totalGeral)
													);
		}
		return consultaDtos;
	}
	
	public ConsultaResponseDTO listarId(Long id) {
		Optional<Consulta> consulta = repoConsulta.findById(id);
		
		if(!consulta.isPresent()) {
			throw new ConsultaException("Id "+ id + " não tem nenhuma consulta associada");
		}
		
		List<ProcedimentosResponseDTO> procDto = new ArrayList<>();
		Double totalGeral = 0.;
		
		for (Procedimentos proc : consulta.get().getProcedimentos()) {
			
			Double subtotal = proc.getQuantidade() * proc.getValorProcedimento();
			totalGeral += subtotal;
			
			procDto.add(new ProcedimentosResponseDTO(proc.getExame().getTipo(), 
														proc.getQuantidade(), 
														proc.getValorProcedimento(), 
														subtotal)
													);
		}
		ConsultaResponseDTO consultaDto = new ConsultaResponseDTO(consulta.get().getDataConsulta(),
																	consulta.get().getPaciente().getNome(),
																	procDto, 
																	totalGeral);
		return consultaDto;
	}

	public ConsultaResponseDTO inserir(ConsultaRequestDTO consultaDtos) {
		Optional<Medico> medicos = repoMedico.findById(consultaDtos.getIdMedico());
		Optional<Paciente> pacientes = repoPaciente.findById(consultaDtos.getIdPaciente());
		Double totalGeral = 0.;
		
		if(!medicos.isPresent()) {
			throw new ConsultaException("Id "+ consultaDtos.getIdMedico() + " não tem nenhum médico associado");
		}
		
		if(!pacientes.isPresent()) {
			throw new ConsultaException("Id "+ consultaDtos.getIdPaciente() + " não tem nenhum paciente associado");
		}
		
		Consulta consulta = new Consulta();
		
		consulta.setDataConsulta(consultaDtos.getDataConsulta());
		consulta.setMedico(medicos.get());
		consulta.setPaciente(pacientes.get());
		consulta.setProcedimentos(new ArrayList<>());
		
		consulta = repoConsulta.save(consulta);
		
		ConsultaResponseDTO consultaDto = new ConsultaResponseDTO(consulta.getDataConsulta(),
																	consulta.getPaciente().getNome(),
																	new ArrayList<>(), 
																	totalGeral);
		
		return consultaDto;
	} 
}
