package org.serratec.grupo.backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Cidade {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@NotBlank(message = "Cidade não pode estar em branco")
	@Size(max = 100, message = "Nome da Cidade não pode exceder 100 caracteres")
    @Column(length = 100, nullable = false)
    private String nome;

	//relacionamento cidade x estado (N:1)
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "estado_id", nullable = false)
    @NotNull(message = "Estado é obrigatório")
    private Estado estado;
	
    //relacionamento cidade x endereco (1:N)
    @JsonManagedReference
	@OneToMany(mappedBy = "cidade")
	private List<Endereco> enderecos;
       
}
