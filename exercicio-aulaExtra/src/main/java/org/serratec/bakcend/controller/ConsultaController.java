package org.serratec.bakcend.controller;

import java.util.List;

import org.serratec.bakcend.DTO.ConsultaRequestDTO;
import org.serratec.bakcend.DTO.ConsultaResponseDTO;
import org.serratec.bakcend.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private ConsultaService service;
	
	@GetMapping
	public ResponseEntity<List<ConsultaResponseDTO>> listar(){
		return ResponseEntity.ok(service.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ConsultaResponseDTO> buscarId(@PathVariable Long id){
		return ResponseEntity.ok(service.listarId(id));
	}
	
	
	@PostMapping("/inserir")
	@ResponseStatus(HttpStatus.CREATED)
	public ConsultaResponseDTO inserir(@Valid @RequestBody ConsultaRequestDTO consultaDto) {
		return service.inserir(consultaDto);
	}
}
