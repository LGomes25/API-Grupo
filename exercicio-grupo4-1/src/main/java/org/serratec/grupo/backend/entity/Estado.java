package org.serratec.grupo.backend.entity;

import java.util.List;

import org.serratec.grupo.backend.enums.SiglaEstado;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Estado {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotBlank(message = "Estado não pode estar em branco")
    @Column(nullable = false, unique = true)
    private String nome;

	@NotNull(message = "Sigla do Estado não pode ser nula")
	@Enumerated(EnumType.STRING)
    @Column(length = 2, nullable = false, unique = true)
    private SiglaEstado sigla;
	
	//relacionamento estado x cidade (1:N)
    @JsonManagedReference
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidades;
	
}
