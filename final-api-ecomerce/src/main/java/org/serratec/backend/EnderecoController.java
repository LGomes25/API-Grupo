package org.serratec.backend;

import org.serratec.backend.dto.EnderecoResponseDTO;
import org.serratec.backend.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService service;
	
	@GetMapping("{cep}")
	public ResponseEntity<EnderecoResponseDTO> buscarCEP(@PathVariable String cep){
		var end = service.buscar(cep);
		
		if(end != null) {
			return ResponseEntity.ok(end);
		}
		return ResponseEntity.notFound().build();
	}
	
}
