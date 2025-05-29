package org.serratec.bakcend.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dataConsulta;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_medico")
	private Medico medico;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "consulta")
	private List<Procedimentos> procedimentos;
}
