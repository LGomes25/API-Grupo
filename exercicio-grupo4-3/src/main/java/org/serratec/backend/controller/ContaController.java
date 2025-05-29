package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.entity.Conta;
import org.serratec.backend.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaService service;

	@GetMapping										//lista todas as contas cadastradas
	public ResponseEntity<List<Conta>> listar(){
		return ResponseEntity.ok(service.listar());
	}

	@PostMapping("/inserir")									//inserir uma conta por vez
	@ResponseStatus(HttpStatus.CREATED)
	public Conta inserir(@RequestBody Conta conta) {
		return service.inserir(conta);
	}
	
}
