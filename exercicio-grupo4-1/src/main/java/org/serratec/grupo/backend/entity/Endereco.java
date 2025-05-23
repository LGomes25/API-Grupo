package org.serratec.grupo.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class Endereco {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@NotBlank(message = "Rua não pode estar em branco")
	@Size(max = 100, message = "Nome da Rua não pode exceder 100 caracteres")
    @Column(length = 100, nullable = false)
    private String rua;

	@NotBlank(message = "o número não pode estar em branco")
    @Column(nullable = false)
    private String numero;

    @Size(max = 300, message = "Complemento não pode exceder 300 caracteres")
    @Column(length = 300)
    private String complemento;

    @NotBlank(message = "Bairro não pode estar em branco")
    @Column(nullable = false)
    private String bairro;

    //relacionamento endereco x cliente (1:1)
    @OneToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    //relacionamento endereco x cidade (N:1)
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "cidade_id", nullable = false)
    @NotNull(message = "Cidade é obrigatória")
    private Cidade cidade;
    
    
}
