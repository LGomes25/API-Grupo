package org.serratec.grupo.backend.controller;

import java.util.List;

import org.serratec.grupo.backend.entity.Cidade;
import org.serratec.grupo.backend.repository.CidadeRepository;
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
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
    private CidadeRepository repository;

    @GetMapping
    public ResponseEntity<List<Cidade>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
        			.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cidade> criar(@Valid @RequestBody Cidade cidade) {
        return ResponseEntity.ok(repository.save(cidade));
    }

    @PostMapping("/lote")
    public ResponseEntity<List<Cidade>> criarEmLote(@Valid @RequestBody List<Cidade> cidades) {
        return ResponseEntity.ok(repository.saveAll(cidades));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long id, @Valid @RequestBody Cidade novoCidade) {
    	if (!repository.existsById(id)) {
	    	throw new EntityNotFoundException("Cidade com ID " + id + " não encontrado ou inexistente!");
	    } 
	    	novoCidade.setId(id);
	    	return ResponseEntity.ok(repository.save(novoCidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    	if (!repository.existsById(id)) {
	    	throw new EntityNotFoundException("Cidade com ID " + id + " não encontrado ou inexistente!");
	    }else {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
