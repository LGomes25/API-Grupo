package org.serratec.grupo.backend.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.grupo.backend.entity.Setor;
import org.serratec.grupo.backend.repository.SetorRepository;
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
@RequestMapping("/setor")
public class SetorController {

	@Autowired
	private SetorRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Setor inserir(@Valid @RequestBody Setor setor) {
		return repository.save(setor);
	}

	@GetMapping
	public List<Setor> listar() {
		return repository.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Setor> listarPorId(@PathVariable Long id) {
		Optional<Setor> setor = repository.findById(id);
		if (!setor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(setor.get());
	}

	@PostMapping("/lista")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Setor> inserir(@Valid @RequestBody List<Setor> setor) {
		return repository.saveAll(setor);

	}

	@PutMapping("{id}")
	public ResponseEntity<Setor> atualizar(@PathVariable Long id, @Valid @RequestBody Setor setor) {
		if (repository.existsById(id)) {
			setor.setId(id);
			return ResponseEntity.ok(repository.save(setor));
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

