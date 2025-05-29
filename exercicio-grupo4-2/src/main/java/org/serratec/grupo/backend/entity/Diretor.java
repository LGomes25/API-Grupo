package org.serratec.grupo.backend.entity;

import org.serratec.grupo.backend.enums.NivelHierarquico;

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
public class Diretor extends Funcionario{

	@NotNull(message = "Nível hierárquico obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private NivelHierarquico nivelHierarquico;
	
    @Override										//heranca de superclass
    @JsonBackReference(value = "diretor-setor")
    public Setor getSetor() {
        return this.setor;
    }

    @Override										//heranca de superclass
    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
