package org.serratec.bakcend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Nave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	Long id;
	private String nome;
	private Integer capacidade;
	private String status;
	
	@OneToMany(mappedBy = "nave")
	@JsonManagedReference
	private List<Colono> colonos;
}
