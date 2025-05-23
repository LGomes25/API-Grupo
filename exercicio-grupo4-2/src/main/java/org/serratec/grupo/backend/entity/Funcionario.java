package org.serratec.grupo.backend.entity;


import org.hibernate.validator.constraints.br.CPF;
import org.serratec.grupo.backend.enums.Turno;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

@MappedSuperclass
public abstract class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@NotBlank(message = "Nome não pode ser em branco")
	@Column(nullable = false)
	protected String nome;
	
	@NotBlank(message = "Obrigatório preencher o CPF")
	@CPF(message = "Número de CPF incorreto")
	@Column(nullable = false, unique = true, length = 14)
	protected String cpf;
	
	@NotNull(message = "Obrigatório preencher o valor do salário")
	protected Double salario;
	
	@NotNull(message = "Turno definido incorretamente")
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
	protected Turno turno;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_setor")
	protected Setor setor;
}
