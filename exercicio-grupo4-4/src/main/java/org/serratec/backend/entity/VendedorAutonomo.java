package org.serratec.backend.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class VendedorAutonomo extends Vendedor {

	@NotNull(message = "Comissão não pode ser nula")
	@Column(precision = 13, scale = 2)
	private BigDecimal comissao;
	
}
