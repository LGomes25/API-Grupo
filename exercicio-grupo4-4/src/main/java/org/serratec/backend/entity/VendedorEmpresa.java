package org.serratec.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class VendedorEmpresa extends Vendedor {

	@NotBlank(message = "Número da carteira de trabalho não pode ser vazio")
	@Column(name = "num_cart_trab")
	private String numCartTrab; 
	
	@NotNull(message = "Data de admissão não pode ser nula")
	@PastOrPresent(message = "Data de admissão deve ser passado ou presente")
	private LocalDate dataAdmissao;
	
	
}

