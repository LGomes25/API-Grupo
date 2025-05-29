package org.serratec.grupo.backend.entity;

import org.serratec.grupo.backend.enums.TipoGerencia;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Gerente extends Funcionario {

	@NotNull(message = "Tipo de gerencia incorreta")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoGerencia tipo;

	@Override									 // heranca de superclass
	@JsonBackReference(value = "gerente-setor")
	public Setor getSetor() {
		return this.setor;
	}

	@Override									 // heranca de superclass
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}
