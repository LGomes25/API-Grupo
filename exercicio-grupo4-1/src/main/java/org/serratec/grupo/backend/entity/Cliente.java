package org.serratec.grupo.backend.entity;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Nome não pode ser em branco")
	@Column(nullable = false)
	private String nome;
	
	@NotBlank(message = "Obrigatório preencher o CPF")
	@CPF(message = "Número de CPF incorreto")
	@Column(nullable = false, unique = true, length = 14)
	private String cpf;
	
	@NotBlank(message = "Obrigatório preencher o email")
	@Email(message = "Formato de e-mail inválido")
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	
    //relacionamento cliente x endereco (1:1)
	@JsonIgnore									//evita que apareça campos null
	@OneToOne(mappedBy = "cliente")				//opcional não necessário para este relacionamento 1:1
	private Endereco endereco;
	
	//relacionamento cliente x pedidos (1:N)
    @JsonManagedReference
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
}
