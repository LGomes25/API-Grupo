package org.serratec.grupo.backend.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.serratec.grupo.backend.enums.Status;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Data do pedido nula ou Inválida")
	private LocalDate dataPedido;
	
	@NotNull(message = "Hora do pedido nula ou Inválida")
	private LocalTime horaPedido;
	
	@NotNull(message = "Data de entrega nula ou Inválida")
	@FutureOrPresent(message = "Data de entrega não pode ser no passado")
	private LocalDate dataEntrega;
	
	@NotNull(message = "Status do pedido inválido")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	//relacionamento pedido x cliente (N:1)
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "Cliente é obrigatório")
    private Cliente cliente;
}
