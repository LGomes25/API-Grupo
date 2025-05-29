package org.serratec.backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class LancamentoVendas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigoVenda; 
	
	@CurrentTimestamp
	private LocalDateTime dataVenda; 
	
	@NotNull(message = "Valor da venda não pode ser nulo")
	@Column(precision = 13, scale = 2)
	private BigDecimal valorVenda; 
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_vendedor", nullable = false)
	private Vendedor vendedor;

}
