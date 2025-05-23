package org.serratec.grupo.backend.entity;

import java.util.ArrayList;
import java.util.List;

import org.serratec.grupo.backend.enums.NomeSetor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Setor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O nome do setor é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private NomeSetor nomeSetor;

	@JsonIgnore
	@JsonManagedReference(value = "gerente-setor")
	@OneToMany(mappedBy = "setor")
	private List<Gerente> gerentes = new ArrayList<>();

	@JsonIgnore
	@JsonManagedReference(value = "diretor-setor")
	@OneToMany(mappedBy = "setor")
	private List<Diretor> diretores = new ArrayList<>();

}
