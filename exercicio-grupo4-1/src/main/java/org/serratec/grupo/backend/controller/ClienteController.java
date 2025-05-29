package org.serratec.grupo.backend.controller;

import java.util.List;

import org.serratec.grupo.backend.entity.Cliente;
import org.serratec.grupo.backend.repository.ClienteRepository;
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
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
    private ClienteRepository repository;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
        			.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(repository.save(cliente));
    }

    @PostMapping("/lote")
    public ResponseEntity<List<Cliente>> criarEmLote(@Valid @RequestBody List<Cliente> clientes) {
        return ResponseEntity.ok(repository.saveAll(clientes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente novoCliente) {
    	if (!repository.existsById(id)) {
	    	throw new EntityNotFoundException("Cliente com ID " + id + " não encontrado ou inexistente!");
	    } 
	    	novoCliente.setId(id);
	    	return ResponseEntity.ok(repository.save(novoCliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    	if (!repository.existsById(id)) {
	    	throw new EntityNotFoundException("Cliente com ID " + id + " não encontrado ou inexistente!");
	    }else {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
