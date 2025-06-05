package org.serratec.backend.dto;

import java.time.LocalDate;

import org.serratec.backend.entity.Funcionario;

public class FuncionarioResponseDTO {
	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private Double salario;

	public FuncionarioResponseDTO(Funcionario funcionario) {
		this.id = funcionario.getId();
		this.nome = funcionario.getNome();
		this.dataNascimento = funcionario.getDataNascimento();
		this.salario = funcionario.getSalario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

}
