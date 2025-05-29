package org.serratec.grupo.backend.controller;

import java.util.List;

import org.serratec.grupo.backend.entity.Endereco;
import org.serratec.grupo.backend.repository.EnderecoRepository;
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
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
    private EnderecoRepository repository;

    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
        			.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Endereco> criar(@Valid @RequestBody Endereco endereco) {
        return ResponseEntity.ok(repository.save(endereco));
    }

    @PostMapping("/lote")
    public ResponseEntity<List<Endereco>> criarEmLote(@Valid @RequestBody List<Endereco> enderecos) {
        return ResponseEntity.ok(repository.saveAll(enderecos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @Valid @RequestBody Endereco novoEndereco) {
    	if (!repository.existsById(id)) {
	    	throw new EntityNotFoundException("Endereco com ID " + id + " não encontrado ou inexistente!");
	    } 
	    	novoEndereco.setId(id);
	    	return ResponseEntity.ok(repository.save(novoEndereco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    	if (!repository.existsById(id)) {
	    	throw new EntityNotFoundException("Endereco com ID " + id + " não encontrado ou inexistente!");
	    }else {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
