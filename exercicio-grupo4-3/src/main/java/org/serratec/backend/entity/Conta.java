package org.serratec.backend.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	@NotBlank(message = "O numero da conta não pode ser nulo")
	private String numeroConta;
	
	@Column(nullable = false)
	@NotBlank(message = "O nome do titular não pode ser nulo")
	private String nomeTitular;
	
	@NotBlank(message = "O CPF não pode estar em branco")
	@CPF
	private String cpf;
	
	@NotNull(message = "O valor não deve ser nulo ou em branco")
	@PositiveOrZero(message = "O valor deverá ser positivo")
	@Column(precision = 13, scale = 2)
	private BigDecimal saldo;

	@JsonManagedReference(value = "origem")
	@OneToMany(mappedBy = "contaOrigem")
	private List<Transacao> transacoesOrigem = new ArrayList<>();
	
	@JsonManagedReference(value = "destino")
	@OneToMany(mappedBy = "contaDestino")
	private List<Transacao> transacoesDestino = new ArrayList<>();
}
