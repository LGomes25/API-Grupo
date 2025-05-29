package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.entity.Transacao;
import org.serratec.backend.service.TransacaoService;
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
@RequestMapping("/transacao")
public class TransacaoController {

	@Autowired
	private TransacaoService service;
	
	@GetMapping
	public ResponseEntity<List<Transacao>> listar(){
		return ResponseEntity.ok(service.listar());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Transacao inserir(@RequestBody Transacao transacao) {
		return service.realizarPix(transacao);
	}
}
