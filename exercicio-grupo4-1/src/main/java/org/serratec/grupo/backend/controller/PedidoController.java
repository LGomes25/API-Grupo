package org.serratec.grupo.backend.controller;

import java.util.List;

import org.serratec.grupo.backend.entity.Pedido;
import org.serratec.grupo.backend.repository.PedidoRepository;
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
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
    private PedidoRepository repository;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
        			.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@Valid @RequestBody Pedido pedido) {
        return ResponseEntity.ok(repository.save(pedido));
    }

    @PostMapping("/lote")
    public ResponseEntity<List<Pedido>> criarEmLote(@Valid @RequestBody List<Pedido> pedidos) {
        return ResponseEntity.ok(repository.saveAll(pedidos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @Valid @RequestBody Pedido novoPedido) {
    	if (!repository.existsById(id)) {
	    	throw new EntityNotFoundException("Pedido com ID " + id + " não encontrado ou inexistente!");
	    } 
	    	novoPedido.setId(id);
	    	return ResponseEntity.ok(repository.save(novoPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    	if (!repository.existsById(id)) {
	    	throw new EntityNotFoundException("Pedido com ID " + id + " não encontrado ou inexistente!");
	    }else {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
