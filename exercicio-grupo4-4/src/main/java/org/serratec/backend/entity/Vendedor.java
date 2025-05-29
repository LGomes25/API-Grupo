package org.serratec.backend.entity;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

//@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Vendedor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long codigoVendedor; 
	
	@NotBlank(message = "Nome não pode ser vazio")
	protected String nome; 

	@NotBlank(message = "Email não pode ser vazio")
	@Email(message = "Email com formato inválido")
	protected String email; 
	
	@DecimalMin(value = "1518.0", message ="Salário deve ser igual ou superior ao salário minimo")
	@NotNull(message = "Salário não pode ser nulo")
	@Column(precision = 13, scale = 2)
	protected BigDecimal salario;
	
}
