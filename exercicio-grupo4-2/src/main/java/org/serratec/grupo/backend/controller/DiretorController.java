package org.serratec.grupo.backend.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.grupo.backend.entity.Diretor;
import org.serratec.grupo.backend.repository.DiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/diretor")
public class DiretorController {

	@Autowired
	private DiretorRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Diretor inserir(@Valid @RequestBody Diretor diretor) {
		return repository.save(diretor);
	}

	@GetMapping
	public List<Diretor> listar() {
		return repository.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Diretor> listarPorId(@PathVariable Long id) {
		Optional<Diretor> diretor = repository.findById(id);
		if (!diretor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(diretor.get());
	}

	@PostMapping("/lista")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Diretor> inserir(@Valid @RequestBody List<Diretor> diretor) {
		return repository.saveAll(diretor);

	}

	@PutMapping("{id}")
	public ResponseEntity<Diretor> atualizar(@PathVariable Long id, @Valid @RequestBody Diretor diretor) {
		if (repository.existsById(id)) {
			diretor.setId(id);
			return ResponseEntity.ok(repository.save(diretor));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}

