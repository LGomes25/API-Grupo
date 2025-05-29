package org.serratec.grupo.backend.controller;

import java.util.List;

import org.serratec.grupo.backend.entity.Estado;
import org.serratec.grupo.backend.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
    private EstadoRepository repository;

    @GetMapping
    public ResponseEntity<List<Estado>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
        			.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estado> criar(@Valid @RequestBody Estado estado) {
        return ResponseEntity.ok(repository.save(estado));
    }

    @PostMapping("/lote")
    public ResponseEntity<List<Estado>> criarEmLote(@Valid @RequestBody List<Estado> estados) {
        return ResponseEntity.ok(repository.saveAll(estados));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long id, @Valid @RequestBody Estado novoEstado) {
    	if (!repository.existsById(id)) {
	    	throw new EntityNotFoundException("Estado com ID " + id + " não encontrado ou inexistente!");
	    } 
	    	novoEstado.setId(id);
	    	return ResponseEntity.ok(repository.save(novoEstado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    	if (!repository.existsById(id)) {
	    	throw new EntityNotFoundException("Estado com ID " + id + " não encontrado ou inexistente!");
	    }else {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
