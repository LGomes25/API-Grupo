package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.LancamentoVendasRequestDTO;
import org.serratec.backend.dto.LancamentoVendasResponseDTO;
import org.serratec.backend.service.LancamentoVendasService;
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

@Validated
@RestController
@RequestMapping("/lancamentos")
public class LancamentoVendasController {

	@Autowired
	private LancamentoVendasService service;
	
	@GetMapping
	public ResponseEntity<List<LancamentoVendasResponseDTO>> listar(){
		return ResponseEntity.ok(service.listar());
	}
	
	
	@GetMapping("/{id}")
    public ResponseEntity<LancamentoVendasResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LancamentoVendasResponseDTO inserir(@RequestBody @Valid LancamentoVendasRequestDTO lancamentoRequest) {
	    return service.inserir(lancamentoRequest);
	}

	
}
