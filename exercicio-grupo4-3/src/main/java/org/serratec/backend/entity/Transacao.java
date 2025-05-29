package org.serratec.backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "data_hora", updatable = false)
    @CreationTimestamp
	private LocalDateTime dataHora;
	
	@NotNull(message = "O valor não deve ser nulo ou em branco")
	@Positive(message = "O valor deverá ser maior que zero")
	@Column(precision = 13, scale = 2)
	private BigDecimal valor;
	
	@JsonBackReference(value = "origem")
	@ManyToOne
	@JoinColumn(name = "id_conta_origem")
	private Conta contaOrigem;
	
	
	@JsonBackReference(value = "destino")
	@ManyToOne
	@JoinColumn(name = "id_conta_destino")
	private Conta contaDestino;
	
	
}
