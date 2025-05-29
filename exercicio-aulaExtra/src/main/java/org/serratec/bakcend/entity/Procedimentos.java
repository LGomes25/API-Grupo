package org.serratec.bakcend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Procedimentos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantidade;
	private Double valorProcedimento;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_consulta")
	private Consulta consulta;
	
	@ManyToOne
	@JoinColumn(name = "id_exame")
	private Exame exame;
}
